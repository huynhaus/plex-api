package com.plex.album;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Retrieves album related information.
 */
@Slf4j
@RestController
public class AlbumController {
    
    private final AlbumService albumService;

    /**
     * Constructor that will inject all necessary dependencies.
     *
     * @param albumService the {@link AlbumService}
     */
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Retrieves all available albums.
     *
     * @return a list of albums
     */
    @GetMapping("albums")
    public List<Album> getAllAlbums() {
        return this.albumService.retrieveAlbums();
    }
}
