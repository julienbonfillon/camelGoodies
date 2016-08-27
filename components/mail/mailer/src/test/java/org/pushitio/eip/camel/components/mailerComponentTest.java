package org.pushitio.eip.camel.components;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class mailerComponentTest extends CamelTestSupport {

    //@Test
    public void testmailer() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);       
        
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("mailer://foo")
                  .to("mailer://bar?url=smtp.gmail.com&port=587&crypto=TLS&login=testown11@gmail.com&password=Mexico01#&from=testown11@gmail.com&fromUserFriendly=HubAlert&replyTo=testown11@gmail.com&attempts=3")
                  .to("mock:result");
            }
        };
    }
}
