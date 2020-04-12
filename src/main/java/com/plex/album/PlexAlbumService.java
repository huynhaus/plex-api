package com.plex.album;

import com.plex.server.models.Directory;
import com.plex.server.models.MediaContainer;
import com.plex.server.models.PlexResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private final AlbumMapper albumMapper;

    /**
     * Constructor to inject dependencies.
     *
     * @param restTemplate the {@link RestTemplate}
     */
    public PlexAlbumService(RestTemplate restTemplate, AlbumMapper albumMapper) {
        this.restTemplate = restTemplate;
        this.albumMapper = albumMapper;
    }

    /**
     * Gets a list of albums from Plex.
     *
     * @return the list of albums
     */
    public List<Album> getAlbums() {
        final PlexResponse response = this.restTemplate
            .getForEntity(this.plexHostUrl + "/library/sections", PlexResponse.class)
            .getBody();

        return this.albumMapper.map(response.getMediaContainer().getDirectories());
    }
}
