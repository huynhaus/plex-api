package com.plex.media;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Retrieves media from the Plex server.
 */
@Slf4j
@RestController
public class MediaController {
    
    private final MediaService mediaService;

    /**
     * Constructor that will inject all necessary dependencies.
     *
     * @param mediaService the {@link MediaService}
     */
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * Retrieves media from the Plex server.
     *
     * @param albumId the album id
     * @param mediaId the media id
     * @return the media
     */
    @GetMapping("albums/{albumId}/medias/{mediaId}")
    public ResponseEntity<byte[]> getAllAlbums(@PathVariable int albumId, @PathVariable int mediaId) {
        return this.mediaService.retrieveMedia(albumId, mediaId);
    }
}
