package org.pushitio.eip.camel.components;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The snmptrap producer.
 */
public class snmptrapProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(snmptrapProducer.class);
    private snmptrapEndpoint endpoint;

    public snmptrapProducer(snmptrapEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());    
    }

}
