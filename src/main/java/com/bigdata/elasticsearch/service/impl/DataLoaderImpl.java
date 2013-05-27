package com.bigdata.elasticsearch.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.aver.fft.RecordListener;
import org.aver.fft.Transformer;
import org.aver.fft.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.MessageHeaders;
import org.springframework.integration.message.GenericMessage;
import org.springframework.stereotype.Component;

import com.bigdata.elasticsearch.domain.Contribution;
import com.bigdata.elasticsearch.service.DataLoader;

/**
 * Reads provided data file, converts it to an instance of Contribution and adds
 * it to a JMS Queue for further processing.
 * 
 * @author Mathew Thomas
 * 
 */
@Component("dataLoader")
public class DataLoaderImpl implements DataLoader {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(DataLoaderImpl.class);

	@Autowired
	@Qualifier("indexerChannel")
	private MessageChannel channel;

	/***/
	public void loadData(File dataFile) {
		LOGGER.info("Loading file ", dataFile.getAbsoluteFile());
		Transformer spec = TransformerFactory
				.getTransformer(Contribution.class);
		final CorrelationId correlationId = new CorrelationId();
		spec.parseFlatFile(dataFile, new RecordListener() {
			public boolean foundRecord(Object o) {
				final Contribution contrib = (Contribution) o;
				Map<String, Object> headers = new HashMap<String, Object>();
				headers.put(MessageHeaders.CORRELATION_ID, correlationId.get());
				GenericMessage<Contribution> msg = new GenericMessage<Contribution>(
						contrib, headers);
				channel.send(msg);
				return true;
			}

			public boolean unresolvableRecord(String rec) {
				// nothing in here for now
				return true;
			}
		});
		LOGGER.info("Done loading file ", dataFile.getAbsoluteFile());
	}
}
