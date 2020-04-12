package com.plex.album;

import com.plex.server.models.Directory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps a {@link Directory} objects to {@link Album} objects.
 */
@Service
public class AlbumMapper {

    /**
     * Maps a list of {@link Directory} to a list of {@link Album}.
     *
     * @param directories the list of directories to map
     * @return the mapped albums
     */
    public List<Album> map(List<Directory> directories) {
        final List<Album> albums = new ArrayList<>();
        for (final Directory directory: directories) {
            albums.add(this.map(directory));
        }
        return albums;
    }

    /**
     * Maps a single {@link Directory} to an {@link Album}.
     *
     * @param directory the directory to map
     * @return the mapped album
     */
    public Album map(Directory directory) {
        final Album album = new Album();
        album.setId(directory.getKey());
        album.setTitle(directory.getTitle());
        album.setType(directory.getType());
        album.setThumbnailUrl(directory.getThumb());
        return album;
    }
}
