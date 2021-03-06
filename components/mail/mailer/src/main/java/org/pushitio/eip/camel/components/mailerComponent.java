package org.pushitio.eip.camel.components;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;

import org.apache.camel.impl.UriEndpointComponent;

/**
 * Represents the component that manages {@link mailerEndpoint}.
 */
public class mailerComponent extends UriEndpointComponent {
    
    public mailerComponent() {
        super(mailerEndpoint.class);
    }

    public mailerComponent(CamelContext context) {
        super(context, mailerEndpoint.class);
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new mailerEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
