package com.plex.media;

import com.plex.library.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Services related to retrieving Library information.
 */
@Service
public class MediaService {

    private PlexMediaService plexMediaService;
    private LibraryService libraryService;

    /**
     * Constructor to inject dependencies.
     *
     * @param plexMediaService the {@link PlexMediaService}
     * @param libraryService the {@link LibraryService}
     */
    public MediaService(PlexMediaService plexMediaService, LibraryService libraryService) {
        this.plexMediaService = plexMediaService;
        this.libraryService = libraryService;
    }

    /**
     * Retrieves the actual bytes of the media.
     *
     * @param albumId the album id
     * @param mediaId the media id
     * @return the media
     */
    public ResponseEntity<byte[]> retrieveMedia(int albumId, int mediaId) {
        return this.plexMediaService.getMedia(this.libraryService.getMedia(albumId, mediaId).getKey());
    }
}
