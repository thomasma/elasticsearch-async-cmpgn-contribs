package com.bigdata.elasticsearch.service.impl;

import com.bigdata.elasticsearch.domain.BulkContributions;

public interface ContributionIndexer {

	public abstract void bulkIndex(BulkContributions bulkContributions);

	public abstract void getContributionsByCandName(String candName,
			Double amtEqGtThan);

}