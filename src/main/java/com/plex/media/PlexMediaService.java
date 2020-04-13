package com.plex.media;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service that will directly call Plex to retrieve library information.
 */
@Slf4j
@Service
public class PlexMediaService {

    private final static List<MediaType> ACCEPTABLE_MEDIA_TYPES = Arrays.asList(
        MediaType.IMAGE_JPEG, MediaType.APPLICATION_OCTET_STREAM);

    @Value("${PLEX_HOST_URL}")
    private String plexHostUrl;

    private final RestTemplate restTemplate;

    /**
     * Constructor to inject dependencies.
     *
     * @param restTemplate the {@link RestTemplate}
     */
    public PlexMediaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Gets the actual bytes of the media.
     *
     * @param key the media key
     * @return the bytes of the media
     */
    public ResponseEntity<byte[]> getMedia(String key) {
        return restTemplate.exchange(this.plexHostUrl + key, HttpMethod.GET, this.getHttpEntity(), byte[].class);
    }

    /**
     * Gets the http entity for media requests.
     *
     * @return the entity
     */
    private HttpEntity<Request> getHttpEntity() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(ACCEPTABLE_MEDIA_TYPES);
        return new HttpEntity<>(headers);
    }
}
