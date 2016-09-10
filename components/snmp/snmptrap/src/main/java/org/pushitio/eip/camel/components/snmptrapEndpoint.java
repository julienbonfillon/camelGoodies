package org.pushitio.eip.camel.components;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

import com.volantetech.vpe.util.net.snmp.agent.Impl.SimpleBindingProps;

/**
 * Represents a snmptrap endpoint.
 */
@UriEndpoint(scheme = "snmptrap", title = "snmptrap", syntax="snmptrap:ip:port", consumerClass = snmptrapConsumer.class, label = "snmptrap")
public class snmptrapEndpoint extends DefaultEndpoint {
    @UriPath @Metadata(required = "true", defaultValue = "127.0.0.1")
    private String ip;
    @UriParam @Metadata(required="true",defaultValue = "162")
    private int port = 10;

    private String agentOid;
    
    private String community;
    
    private String version; 
    
    private Integer retries;
    
    private Long timeout;
    
    
    private String messageOid;
    private String plainTextMessage;
    
    

    public snmptrapEndpoint() {
    }

    public snmptrapEndpoint(String uri, snmptrapComponent component) {
        super(uri, component);
    }

    public snmptrapEndpoint(String endpointUri) {
        super(endpointUri);
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

	/**
	 * @return the plainTextMessage
	 */
	public String getPlainTextMessage() {
		return plainTextMessage;
	}

	/**
	 * @param plainTextMessage the plainTextMessage to set
	 */
	public void setPlainTextMessage(String plainTextMessage) {
		this.plainTextMessage = plainTextMessage;
	}
    
    
    
}
