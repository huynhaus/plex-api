package com.plex.library;

import com.plex.models.api.Album;
import com.plex.models.api.Library;
import com.plex.models.api.Media;
import com.plex.models.plex.PlexResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Service that will directly call Plex to retrieve library information.
 */
@Slf4j
@Service
public class PlexLibraryService {

    @Value("${PLEX_HOST_URL}")
    private String plexHostUrl;

    private final RestTemplate restTemplate;
    private final LibraryMapper libraryMapper;

    /**
     * Constructor to inject dependencies.
     *
     * @param restTemplate the {@link RestTemplate}
     * @param libraryMapper the {@link LibraryMapper}
     */
    public PlexLibraryService(RestTemplate restTemplate, LibraryMapper libraryMapper) {
        this.restTemplate = restTemplate;
        this.libraryMapper = libraryMapper;
    }

    /**
     * Gets the entire Plex library.
     *
     * @return the library
     */
    public Library getLibrary() {
        final Library library = new Library();

        library.setAlbums(this.getAlbums());

        for (Album album: library.getAlbums()) {
            album.setMediaList(this.getAllMedia(album.getId()));
        }

        return library;
    }

    /**
     * Gets a list of all albums in a library.
     *
     * @return the list of albums
     */
    private List<Album> getAlbums() {
        final PlexResponse response = this.restTemplate
            .getForEntity(this.plexHostUrl + "/library/sections", PlexResponse.class)
            .getBody();

        if (response.getMediaContainer() == null || response.getMediaContainer().getDirectories() == null) {
            return new ArrayList<>();
        }
        return this.libraryMapper.mapAlbums(response.getMediaContainer().getDirectories());
    }

    /**
     * Gets a list of media for a certain album.
     *
     * @param albumId the album id
     * @return the list of media
     */
    private List<Media> getAllMedia(int albumId) {
        final PlexResponse response = this.restTemplate
            .getForEntity(this.plexHostUrl + "/library/sections/" + albumId + "/all", PlexResponse.class)
            .getBody();

        if (response.getMediaContainer() == null || response.getMediaContainer().getMetaDataList() == null) {
            return new ArrayList<>();
        }

        return this.libraryMapper.mapMediaList(response.getMediaContainer().getMetaDataList(), albumId);
    }
}
