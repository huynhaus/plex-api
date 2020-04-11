package com.plex.album;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that will directly call Plex to retrieve album information.
 */
@Slf4j
@Service
public class PlexAlbumService {

    @Value("${PLEX_HOST_URL}")
    private String plexHostUrl;

    private final RestTemplate restTemplate;

    /**
     * Constructor to inject dependencies.
     *
     * @param restTemplate the {@link RestTemplate}
     */
    public PlexAlbumService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Album> getAlbums() {

        final String test = this.restTemplate
            .getForEntity(this.plexHostUrl + "/library/sections/1/all", String.class)
            .getBody();

        log.error(test);

        return new ArrayList<>();
    }
}
