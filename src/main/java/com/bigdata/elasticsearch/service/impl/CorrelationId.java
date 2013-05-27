package com.bigdata.elasticsearch.service.impl;

import java.util.UUID;

import com.bigdata.elasticsearch.service.impl.aggregator.MessageReleaseStrategy;

/**
 * Creates stub correlation ids that can be used to set CORRELATION_ID inside
 * JMS headers to relate a group of messages together.
 * 
 * @author Mathew Thomas
 * 
 */
public final class CorrelationId {
	private long resetAfter = MessageReleaseStrategy.MAX_DOCS_TO_BATCH;
	private String correlationId;
	private long counter;

	public CorrelationId() {
		reset();
	}

	String get() {
		if (counter > resetAfter) {
			reset();
		}
		counter++;
		return correlationId;
	}

	void reset() {
		this.correlationId = UUID.randomUUID().toString();
		this.counter = 1l;
	}
}
