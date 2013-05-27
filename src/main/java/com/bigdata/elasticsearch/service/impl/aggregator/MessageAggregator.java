package com.bigdata.elasticsearch.service.impl.aggregator;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bigdata.elasticsearch.domain.BulkContributions;
import com.bigdata.elasticsearch.domain.Contribution;

/**
 * Based on the Spring Integration Aggregator configuration, this class receives
 * the bulk set of messages that are to be aggregated. This bean then can
 * process them as desired or as in this case simply adds them into a
 * serializable container object, which is then placed into the aggregator
 * output channel (see applicationContext.xml).
 * 
 * @author Mathew Thomas
 * 
 */
@Component("messageAggregatorBean")
public class MessageAggregator {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(MessageAggregator.class);

	/**
	 * Returns a container object that holds the list.
	 * 
	 * @param results
	 * @return
	 */
	public BulkContributions aggregate(Collection<Contribution> results) {
		LOGGER.debug("Aggregating {} documents", results.size());
		BulkContributions result = new BulkContributions();
		result.getContributions().addAll(results);
		return result;
	}
}
