package com.plex.album;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Services related to retrieving album information.
 */
@Service
public class AlbumService {

    private PlexAlbumService plexAlbumService;

    /**
     * Constructor to inject dependencies.
     *
     * @param plexAlbumService the {@link PlexAlbumService}
     */
    public AlbumService(PlexAlbumService plexAlbumService) {
        this.plexAlbumService = plexAlbumService;
    }

    /**
     * Retrieves all available albums.
     *
     * @return a list of albums
     */
    public List<Album> retrieveAlbums() {
        return this.plexAlbumService.getAlbums();
    }
}
