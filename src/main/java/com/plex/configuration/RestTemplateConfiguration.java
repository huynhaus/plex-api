package com.plex.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Rest template configuration for all http requests to Plex.
 */
@Configuration
public class RestTemplateConfiguration {

    @Value("${PLEX_AUTH_TOKEN}")
    private String authToken;

    private static final Duration TIMEOUT = Duration.ofMillis(3000);

    /**
     * Configures a {@link RestTemplate} bean.
     *
     * @param builder builder for the {@link RestTemplate}
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .setConnectTimeout(TIMEOUT)
            .setReadTimeout(TIMEOUT)
            .interceptors(new PlexRequestInterceptor(this.authToken))
            .additionalMessageConverters(this.byteArrayHttpMessageConverter())
            .build();
    }

    @Bean public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }
}
