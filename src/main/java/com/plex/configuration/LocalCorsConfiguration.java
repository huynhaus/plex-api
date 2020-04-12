package com.plex.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures the cors policies to allow requests come from different ports
 * such as 4200 to 8080. This is for local development only.
 */
@Configuration
@Profile("local")
public class LocalCorsConfiguration implements WebMvcConfigurer {

    /**
     * Updates the cors registry mapping.
     * @param registry the registry to modify
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*");
    }
}
