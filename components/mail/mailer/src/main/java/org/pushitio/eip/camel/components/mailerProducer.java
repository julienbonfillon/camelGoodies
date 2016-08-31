package org.pushitio.eip.camel.components;

import java.util.List;
import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;

import com.volantetech.vpe.util.net.mail.DestinatorType;
import com.volantetech.vpe.util.net.mail.Destinator;

/**
 * The mailer producer.
 */
public class mailerProducer extends DefaultProducer {
    private mailerEndpoint endpoint;
    private com.volantetech.vpe.util.net.mail.MailAgent _mailSender = null;
    private final com.volantetech.vpe.util.net.mail.SmtpConfiguration _serverCfg = new com.volantetech.vpe.util.net.mail.SmtpConfiguration() ;
    private final com.volantetech.vpe.util.net.mail.DestinatorList _destList = new com.volantetech.vpe.util.net.mail.DestinatorList();
    private com.volantetech.vpe.util.net.mail.AttachementList _attachList = new com.volantetech.vpe.util.net.mail.AttachementList();
    private com.volantetech.vpe.util.net.mail.EmbeddedList _embeddedList = new com.volantetech.vpe.util.net.mail.EmbeddedList();

    public mailerProducer(mailerEndpoint endpoint) {
        super(endpoint);
        this.endpoint = endpoint;
    }
    
    
    
    private ArrayList<Destinator> getDestList(	ArrayList<Destinator> currDestList, 
	    										String tokenizedDestList,
	    										String destListToken,
	    										DestinatorType destType){
    	String[] addDestList= tokenizedDestList.split(destListToken);
    	for(int iLoop = 0; iLoop < addDestList.length;iLoop ++){
    		if(!addDestList[iLoop].isEmpty()){
	    		Destinator newDest = new Destinator();
	    		newDest.iDestType = destType.getValue();
	    		newDest.sDestinaryAddress = addDestList[iLoop];
	    		currDestList.add(newDest);
    		}
    	}
    	return currDestList;
    }

    
    
    
    public void process(Exchange exchange) throws Exception {
    	//log.info(exchange.getIn().getBody(String.class));
    	_serverCfg.setSmtpHost(this.endpoint.getUrl());
    	_serverCfg.setSmtpPort(this.endpoint.getPort());
    	String crypto = this.endpoint.getCrypto();
    	
    	if(null!=crypto)
	    	if(crypto.equals("SSL"))
	            _serverCfg.isSSL(true);
	        else if(crypto.equals("TLS"))
	            _serverCfg.isTLS(true);
    	
    	_serverCfg.setLogin(this.endpoint.getLogin());
    	_serverCfg.setPassword(this.endpoint.getPassword());
    	
    	/*
    	 * Tokenize the to/cc/bcc destination lists
    	 * 
    	 * */
    	log.info("to:"+this.endpoint.getTo());
    	
    	if(null!=this.endpoint.getTo())
    		this._destList.destinary = getDestList(	this._destList.destinary, 
													this.endpoint.getTo(),
													";",
													DestinatorType.TO);
    	
    	log.info("cc:"+this.endpoint.getCc());
    	if(null!=this.endpoint.getCc())
    		this._destList.destinary = getDestList(	this._destList.destinary, 
													this.endpoint.getCc(),
													";",
													DestinatorType.CC);
    	
    	log.info("bcc:"+this.endpoint.getBcc());
    	if(null!=this.endpoint.getBcc())
    		this._destList.destinary = getDestList(	this._destList.destinary, 
													this.endpoint.getBcc(),
													";",
													DestinatorType.BCC);
    	
    	this._mailSender = new com.volantetech.vpe.util.net.mail.MailAgent(this._serverCfg);
    	
    	if(null!= exchange.getIn().getHeader("ATTACHEMENT_LIST"))
    		_attachList = (com.volantetech.vpe.util.net.mail.AttachementList)exchange.getIn().getHeader("ATTACHEMENT_LIST");
    	if(null!= exchange.getIn().getHeader("EMBEDDED_LIST"))
    		_embeddedList = (com.volantetech.vpe.util.net.mail.EmbeddedList)exchange.getIn().getHeader("EMBEDDED_LIST");
    	
    	this._mailSender.Send(	this._destList,
		    					exchange.getIn().getBody(String.class),
		    					this.endpoint.getMailSubject(),
		    					this.endpoint.getFrom(),
		    					this.endpoint.getFromUserFriendly(),
		    					this.endpoint.getReplyTo(),
		    					this._attachList,
		    					this._embeddedList);
    }

}
