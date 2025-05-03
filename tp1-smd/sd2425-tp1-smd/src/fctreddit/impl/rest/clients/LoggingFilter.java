package fctreddit.impl.rest.clients;
import java.io.IOException;
import java.util.logging.Logger;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ClientRequestFilter, ClientResponseFilter {

	private static Logger Log = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        Log.fine("Request: " + requestContext.getMethod() + " " + requestContext.getUri());
        requestContext.getHeaders().forEach((key, value) -> Log.fine("Header: " + key + " = " + value));
        if (requestContext.hasEntity()) {
            Log.fine("Entity: " + requestContext.getEntity().toString());
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        Log.fine("Response Status: " + responseContext.getStatus());
        responseContext.getHeaders().forEach((key, value) -> Log.fine("Header: " + key + " = " + value));
    }
}
