package org.pushitio.eip.camel.components;

import java.net.URI;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

/**
 * Represents a snmptrap endpoint.
 */
@UriEndpoint(scheme = "snmptrap", title = "snmptrap", syntax="snmptrap:ip:port", consumerClass = snmptrapConsumer.class, label = "snmptrap")
public class snmptrapEndpoint extends DefaultEndpoint {
	
	private static final String URI_ERROR = "Invalid URI. Format must be of the form snmptrap:udp://<host>[:port]";
	
	
    @UriPath(description = "The IP of the Monitor") 
    @Metadata(required = "true")
    private String ip;
    @UriPath(description = "The port of the Monitor (default = 162 )", defaultValue = "162")  
    @Metadata(defaultValue = "162")
    private int port = 162;
    @UriParam (description = "The OID related to the agent emulated by the camel endpoint") 
    @Metadata(required="true")
    private String agentOid;
    @UriParam (description = "Qualify the type of community",enums="public", defaultValue = "public") 
    @Metadata(required="false", defaultValue = "public")
    private String community = "public";
    @UriParam (description ="Specify the version of the SNMP Agent", enums = "V2c")
    @Metadata(required="true")
    private String version; 
    @UriParam @Metadata(defaultValue = "0")
    private Integer retries = 0;
    @UriParam @Metadata(defaultValue = "60000")
    private Long timeout = new Long(60000);
    @UriParam (description = "Message related OID: if present, the message body will be sent and binded to messageOID") 
    @Metadata(required="false")
    private String messageOid;
    

    public snmptrapEndpoint() {
    }

    public snmptrapEndpoint(String endpointUri,String remaining, snmptrapComponent component) throws Exception{
        super(endpointUri, component);
        URI uri = new URI(remaining);
        
        if(!uri.getScheme().equalsIgnoreCase("udp"))
        	throw new IllegalArgumentException(URI_ERROR);
        
        ip = uri.getHost();
        if (ip == null)
            throw new IllegalArgumentException(URI_ERROR);
        
        port = uri.getPort() == -1 ? 162 : uri.getPort();
    }
    
    public Producer createProducer() throws Exception {
        return new snmptrapProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new snmptrapConsumer(this, processor);
    }

    public boolean isSingleton() {
        return true;
    }

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the agentOid
	 */
	public String getAgentOid() {
		return agentOid;
	}

	/**
	 * @param agentOid the agentOid to set
	 */
	public void setAgentOid(String agentOid) {
		this.agentOid = agentOid;
	}

	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * @param community the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the retries
	 */
	public Integer getRetries() {
		return retries;
	}

	/**
	 * @param retries the retries to set
	 */
	public void setRetries(Integer retries) {
		this.retries = retries;
	}

	/**
	 * @return the timeout
	 */
	public Long getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the messageOid
	 */
	public String getMessageOid() {
		return messageOid;
	}

	/**
	 * @param messageOid the messageOid to set
	 */
	public void setMessageOid(String messageOid) {
		this.messageOid = messageOid;
	}
    
}
