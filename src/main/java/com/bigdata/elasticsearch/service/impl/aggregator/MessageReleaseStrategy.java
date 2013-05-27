package com.bigdata.elasticsearch.service.impl.aggregator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bigdata.elasticsearch.domain.Contribution;

/**
 * Defines the release strategy which is used to batch up and release the
 * aggregated set of messages to the next step in the processing pipeline (in
 * this case bulk index into ElasticSearch).
 * 
 * @author Mathew Thomas
 */
@Component("messageReleaseStrategyBean")
public class MessageReleaseStrategy {
	public final static int MAX_DOCS_TO_BATCH = 100;

	public boolean canRelease(List<Contribution> messages) {
		return (messages.size() > MAX_DOCS_TO_BATCH);
	}
}
