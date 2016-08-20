package org.pushitio.eip.camel.components;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The mailer producer.
 */
public class mailerProducer extends DefaultProducer {
    private static final Logger LOG = LoggerFactory.getLogger(mailerProducer.class);
    private mailerEndpoint endpoint;

    public mailerProducer(mailerEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody());    
    }

}
