package org.pushitio.eip.camel.components;

import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;

import org.apache.camel.impl.UriEndpointComponent;

/**
 * Represents the component that manages {@link snmptrapEndpoint}.
 */
public class snmptrapComponent extends UriEndpointComponent {
    
    public snmptrapComponent() {
        super(snmptrapEndpoint.class);
    }

    public snmptrapComponent(CamelContext context) {
        super(context, snmptrapEndpoint.class);
    }

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new snmptrapEndpoint(uri,remaining, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
