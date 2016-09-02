package org.pushitio.eip.camel.components;

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
    	com.volantetech.vpe.util.net.mail.SmtpConfiguration serverCfg = new com.volantetech.vpe.util.net.mail.SmtpConfiguration();
    	
    	//log.info(exchange.getIn().getBody(String.class));
    	serverCfg.setSmtpHost(this.endpoint.getUrl());
    	serverCfg.setSmtpPort(this.endpoint.getPort());
    	String crypto = this.endpoint.getCrypto();
    	
    	if(null!=crypto)
	    	if(crypto.equals("SSL"))
	            serverCfg.isSSL(true);
	        else if(crypto.equals("TLS"))
	            serverCfg.isTLS(true);
    	
    	serverCfg.setLogin(this.endpoint.getLogin());
    	serverCfg.setPassword(this.endpoint.getPassword());
    	
    	/*
    	 * Tokenize the to/cc/bcc destination lists
    	 * 
    	 * */
    	log.info("to:"+this.endpoint.getTo());
    	
    	com.volantetech.vpe.util.net.mail.DestinatorList destList = new com.volantetech.vpe.util.net.mail.DestinatorList();
    	
    	if(null!=this.endpoint.getTo())
    		destList.destinary = getDestList(	destList.destinary, 
												this.endpoint.getTo(),
												";",
												DestinatorType.TO);
    	
    	log.info("cc:"+this.endpoint.getCc());
    	if(null!=this.endpoint.getCc())
    		destList.destinary = getDestList(	destList.destinary, 
												this.endpoint.getCc(),
												";",
												DestinatorType.CC);
    	
    	log.info("bcc:"+this.endpoint.getBcc());
    	if(null!=this.endpoint.getBcc())
    		destList.destinary = getDestList(	destList.destinary, 
												this.endpoint.getBcc(),
												";",
												DestinatorType.BCC);
    	
    	com.volantetech.vpe.util.net.mail.MailAgent mailSender = new com.volantetech.vpe.util.net.mail.MailAgent(serverCfg);
    	com.volantetech.vpe.util.net.mail.AttachementList attachList = new com.volantetech.vpe.util.net.mail.AttachementList();
        com.volantetech.vpe.util.net.mail.EmbeddedList embeddedList = new com.volantetech.vpe.util.net.mail.EmbeddedList();
    	
    	if(null!= exchange.getIn().getHeader("ATTACHEMENT_LIST"))
    		attachList = (com.volantetech.vpe.util.net.mail.AttachementList)exchange.getIn().getHeader("ATTACHEMENT_LIST");
    	if(null!= exchange.getIn().getHeader("EMBEDDED_LIST"))
    		embeddedList = (com.volantetech.vpe.util.net.mail.EmbeddedList)exchange.getIn().getHeader("EMBEDDED_LIST");
    	
    	mailSender.Send(	destList,
	    					exchange.getIn().getBody(String.class),
	    					this.endpoint.getMailSubject(),
	    					this.endpoint.getFrom(),
	    					this.endpoint.getFromUserFriendly(),
	    					this.endpoint.getReplyTo(),
	    					attachList,
	    					embeddedList);
    }
}
