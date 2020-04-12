package com.plex.library;

import com.plex.models.api.Library;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Retrieves library related information.
 */
@Slf4j
@RestController
public class LibraryController {
    
    private final LibraryService libraryService;

    /**
     * Constructor that will inject all necessary dependencies.
     *
     * @param libraryService the {@link LibraryService}
     */
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    /**
     * Retrieves the Plex library.
     *
     * @return the library
     */
    @GetMapping("library")
    public Library getAllAlbums() {
        return this.libraryService.retrieveLibrary();
    }
}
