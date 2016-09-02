package org.pushitio.eip.camel.components;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;

/**
 * Represents a mailer endpoint.
 */
@UriEndpoint(scheme = "mailer", title = "mailer", syntax="mailer:name", consumerClass = mailerConsumer.class, label = "mailer")
public class mailerEndpoint extends DefaultEndpoint {
    @UriPath @Metadata(required = "true")
    private String url;
    @UriPath @Metadata(required = "true")
    private Integer port;
    @UriPath @Metadata(required = "false")
    private String crypto;
    @UriPath @Metadata(required = "false")
    private String login;
    @UriPath @Metadata(required = "false")
    private String password;
    @UriPath @Metadata(required = "true")
    private Integer attempts;
    @UriPath @Metadata(required = "false")
    private String from;
    @UriPath @Metadata(required = "true")
    private String to;
    @UriPath @Metadata(required = "false")
    private String cc;
    @UriPath @Metadata(required = "false")
    private String bcc;
    @UriPath @Metadata(required = "false")
    private String fromUserFriendly;
    @UriPath @Metadata(required = "false")
    private String replyTo;
    
    
    @UriPath @Metadata(required = "false")
    private String mailSubject;
    

    public mailerEndpoint() {
    }

    public mailerEndpoint(String uri, mailerComponent component) {
        super(uri, component);
    }

    @SuppressWarnings("deprecation")
	public mailerEndpoint(String endpointUri) {
        super(endpointUri);
    }

    public Producer createProducer() throws Exception {
        return new mailerProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return new mailerConsumer(this, processor);
    }

    public boolean isSingleton() {
        return false;
    }

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the crypto
	 */
	public String getCrypto() {
		return crypto;
	}

	/**
	 * @param crypto the crypto to set
	 */
	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the attempts
	 */
	public Integer getAttempts() {
		return attempts;
	}

	/**
	 * @param attempts the attempts to set
	 */
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the fromUserFriendly
	 */
	public String getFromUserFriendly() {
		return fromUserFriendly;
	}

	/**
	 * @param fromUserFriendly the fromUserFriendly to set
	 */
	public void setFromUserFriendly(String fromUserFriendly) {
		this.fromUserFriendly = fromUserFriendly;
	}

	/**
	 * @return the replyTo
	 */
	public String getReplyTo() {
		return replyTo;
	}

	/**
	 * @param replyTo the replyTo to set
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the bcc
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * @param bcc the bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
    
}
