package com.bigdata.elasticsearch.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Holds multiple Contribution objects for bulk delivery to JMS Queue for
 * indexing into ElasticSearch.
 * 
 * @author Mathew Thomas
 * 
 */
public class BulkContributions implements Serializable {
	private static final long serialVersionUID = -5579373113599973736L;

	private Collection<Contribution> contributions = new ArrayList<Contribution>();

	public Collection<Contribution> getContributions() {
		return contributions;
	}
}
