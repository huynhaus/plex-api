package com.plex.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * Interceptor for all requests to Plex.
 */
@Slf4j
public class PlexRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String authToken;

    /**
     * Constructor.
     *
     * @param authToken the Plex auth token
     */
    public PlexRequestInterceptor(String authToken) {
        this.authToken = authToken;
    }

    /**
     * Adds an auth token to each request to Plex.
     *
     * @param request the request to intercept
     * @param body the body of the request
     * @param execution the {@link ClientHttpRequestExecution}
     * @return the modified request
     * @throws IOException if there's an error modifying the request
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException {

        final URI uri = UriComponentsBuilder.fromHttpRequest(request)
            .queryParam("X-Plex-Token", this.authToken)
            .build()
            .toUri();

        final HttpRequest modifiedRequest = new HttpRequestWrapper(request) {
            @Override
            public URI getURI() {
                return uri;
            }
        };

        return execution.execute(modifiedRequest, body);
    }
}
