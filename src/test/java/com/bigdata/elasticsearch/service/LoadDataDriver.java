package com.bigdata.elasticsearch.service;

import java.io.File;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bigdata.elasticsearch.service.DataLoader;

/**
 * Run to load data into ElasticSearch via an asynchronous model (using JMS &
 * ActiveMQ). Use a Spring Integration (EIP pattern) aggregator pattern to
 * aggregate messages for bulk asynch to reduce calls to ElasticSearch.
 */
public class LoadDataDriver {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		DataLoader loader = ctx.getBean(DataLoader.class);

		// --------------------------------
		// load data - point this to your path
		// --------------------------------
		loader.loadData(new File("/Users/mathew/temp/P00000001-VA.csv"));

		// --------------------------------
		// print total count for verification
		// --------------------------------
		// System.out.println("Total Count of Documents = " +
		// loader.getTotalCount());

		//
		ctx.close();
	}
}
