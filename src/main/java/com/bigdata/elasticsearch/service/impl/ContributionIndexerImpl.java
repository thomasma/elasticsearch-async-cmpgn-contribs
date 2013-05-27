package com.bigdata.elasticsearch.service.impl;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.DestroyFailedException;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.statistical.StatisticalFacetBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bigdata.elasticsearch.domain.BulkContributions;
import com.bigdata.elasticsearch.domain.Contribution;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements methods that interact with ElasticSearch (including the bulk
 * indexer method).
 * 
 * @author Mathew Thomas
 * 
 */
@Component("contributionIndexerBean")
public class ContributionIndexerImpl implements ContributionIndexer {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(ContributionIndexerImpl.class);

	private Client client;

	private ObjectMapper mapper = new ObjectMapper();

	/***/
	public void bulkIndex(BulkContributions bulkContributions) {
		Collection<Contribution> documents = bulkContributions
				.getContributions();
		LOGGER.info("Indexing bulk request of {} documents.", documents.size());
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for (Contribution contribution : documents) {
			String json = null;
			try {
				json = mapper.writeValueAsString(contribution);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			bulkRequest.add(client.prepareIndex("contributions", "year2012",
					UUID.randomUUID().toString()).setSource(json));
		}
		BulkResponse response = bulkRequest.execute().actionGet();
		if (response.hasFailures()) {
			throw new RuntimeException(
					"there was an error indexing the bulk request of "
							+ documents.size() + " documents");
		}
	}

	/***/
	public void getContributionsByCandName(String candName, Double amtEqGtThan) {
		LOGGER.info(
				"Getting contributions for candidate {} and amount equal or greater thab {}",
				candName, amtEqGtThan);
		QueryBuilder matchQuery = QueryBuilders.matchQuery("candNm", candName);
		FilterBuilder contribRangeFilter = FilterBuilders.rangeFilter(
				"contbReceiptAmt").gte(amtEqGtThan);
		StatisticalFacetBuilder facet = FacetBuilders.statisticalFacet("stat1")
				.field("contbReceiptAmt");
		SearchRequestBuilder request = client
				.prepareSearch("contributions")
				.addSort(
						SortBuilders.fieldSort("contbReceiptAmt").order(
								SortOrder.DESC))
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.setQuery(matchQuery)
				.setFilter(contribRangeFilter)
				.addFacet(facet)
				.setFrom(0)
				.setSize(100)
				.addFields("contbrNm", "candNm", "contbrEmployer",
						"contbReceiptAmt");
		System.out.println("SEARCH QUERY: " + request.toString());

		SearchResponse response = request.execute().actionGet();
		SearchHits searchHits = response.getHits();
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit hit : hits) {
			Map<String, SearchHitField> fields = hit.getFields();
			System.out.println(hit.getId() + ", contbrEmployer="
					+ fields.get("contbrEmployer").getValue().toString());
		}
	}

	@PostConstruct
	public void initialize() throws Exception {
		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "elasticsearch").build();
		client = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(
						"localhost", 9300));
	}

	@PreDestroy
	public void destroy() throws DestroyFailedException {
		if (client != null) {
			client.close();
		}
	}
}
