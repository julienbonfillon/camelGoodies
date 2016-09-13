package org.pushitio.eip.camel.components;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.pushitio.eip.camel.components.snmp.SNMPVersion;
import com.volantetech.vpe.util.net.snmp.agent.Impl.V2cTrapImpl;
import com.volantetech.vpe.util.net.snmp.agent.Impl.SimpleBidingProps;
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
		if( SNMPVersion.valueOf(this.endpoint.getVersion()).equals(SNMPVersion.V2c) ){
			V2cTrapImpl trapper = new V2cTrapImpl();
			LOG.debug("Host: "+endpoint.getIp() + "/"+endpoint.getPort());
			LOG.debug("Agent OID: " +endpoint.getAgentOid());
			LOG.debug("Community: " +endpoint.getCommunity());
			LOG.debug("Retries: " +endpoint.getRetries());
			LOG.debug("Timeout: " +endpoint.getTimeout());
			java.util.ArrayList<SimpleBidingProps> bidingProps = new java.util.ArrayList<SimpleBidingProps>();
			if(null != endpoint.getMessageOid() && !endpoint.getMessageOid().isEmpty()){
				bidingProps.add(new SimpleBidingProps(endpoint.getMessageOid(),exchange.getIn().getBody(String.class)));
			}				
			trapper.send(	endpoint.getIp(), 
							endpoint.getPort(),
							endpoint.getAgentOid(), 
							endpoint.getCommunity(), 
							endpoint.getRetries(), 
							endpoint.getTimeout(),
							bidingProps);
		} //Will throw an IllegalArgumentException in case of undefined version value
    }

}
