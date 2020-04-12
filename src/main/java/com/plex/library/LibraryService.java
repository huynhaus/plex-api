package com.plex.library;

import com.plex.models.api.Library;
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
    public Library retrieveLibrary() {
        return this.plexLibraryService.getLibrary();
    }
}
