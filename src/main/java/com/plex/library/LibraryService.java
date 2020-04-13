package com.plex.library;

import com.plex.models.api.Album;
import com.plex.models.api.Library;
import com.plex.models.api.Media;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Services related to retrieving Library information.
 */
@Service
public class LibraryService {

    private PlexLibraryService plexLibraryService;

    /**
     * Constructor to inject dependencies.
     *
     * @param plexLibraryService the {@link PlexLibraryService}
     */
    public LibraryService(PlexLibraryService plexLibraryService) {
        this.plexLibraryService = plexLibraryService;
    }

    /**
     * Retrieves the Plex library.
     *
     * @return the library
     */
    @Cacheable("library-cache")
    public Library getLibrary() {
        return this.plexLibraryService.getLibrary();
    }

    /**
     * Gets a specific {@link Media}.
     *
     * @param albumId the album id
     * @param mediaId the media id
     * @return the media
     */
    public Media getMedia(int albumId, int mediaId) {
        return this.getMedia(this.getAlbum(albumId), mediaId);
    }

    /**
     * Get's an album from the Plex library.
     *
     * @param id the album id
     * @return the album
     */
    private Album getAlbum(int id) {
        for (final Album album: this.getLibrary().getAlbums()) {
            if (album.getId() == id) {
                return album;
            }
        }

        throw new RuntimeException("Cannot find album " + id); // TODO create custom exception with 404
    }

    /**
     * Gets a media from an album.
     *
     * @param album the album to search through
     * @param id the key of the media
     * @return the media
     */
    private Media getMedia(Album album, int id) {
        for (final Media media: album.getMediaList()) {
            if (media.getId() == id) {
                return media;
            }
        }

        throw new RuntimeException("Cannot find album" + album.getId() + " media " + id); // TODO create custom exception with 404
    }
}
