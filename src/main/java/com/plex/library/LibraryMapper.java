package com.plex.library;

import com.plex.models.api.Album;
import com.plex.models.api.Media;
import com.plex.models.plex.Directory;
import com.plex.models.plex.MetaData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper used to map various plex responses to the api models.
 */
@Service
public class LibraryMapper {

    /**
     * Maps a list of {@link Directory} to a list of {@link Album}.
     *
     * @param directories the list of directories to map
     * @return the mapped albums
     */
    public List<Album> mapAlbums(List<Directory> directories) {
        final List<Album> albums = new ArrayList<>();
        for (final Directory directory: directories) {
            albums.add(this.mapAlbum(directory));
        }
        return albums;
    }

    /**
     * Maps a single {@link Directory} to an {@link Album}.
     *
     * @param directory the directory to map
     * @return the mapped album
     */
    public Album mapAlbum(Directory directory) {
        final Album album = new Album();
        album.setId(directory.getKey());
        album.setTitle(directory.getTitle());
        album.setType(directory.getType());
        return album;
    }

    /**
     * Maps a list of {@link MetaData} to a list of {@link Media}.
     *
     * @param metaDataList the list of metadata to map
     * @param albumId the id of the album the media list belongs to
     * @return the mapped medias
     */
    public List<Media> mapMediaList(List<MetaData> metaDataList, int albumId) {
        final List<Media> mediaList = new ArrayList<>();
        for (final MetaData metaData: metaDataList) {
            mediaList.add(this.mapMedia(metaData, albumId));
        }
        return mediaList;
    }

    /**
     * Maps a single {@link MetaData} to an {@link Media}.
     *
     * @param metaData the metaData to map
     * @param albumId the id of the album the media belongs to
     * @return the mapped media
     */
    public Media mapMedia(MetaData metaData, int albumId) {
        final Media media = new Media();
        media.setId(metaData.getMediaList().get(0).getId());
        media.setKey(metaData.getMediaList().get(0).getParts().get(0).getKey());
        media.setTitle(metaData.getTitle());
        media.setSummary(metaData.getSummary());
        media.setType(metaData.getType());
        media.setUrl("/albums/" + albumId + "/media/" + media.getId());
        media.setWidth(metaData.getMediaList().get(0).getWidth());
        media.setHeight(metaData.getMediaList().get(0).getHeight());
        media.setAspectRatio(metaData.getMediaList().get(0).getAspectRatio());
        media.setSize(metaData.getMediaList().get(0).getParts().get(0).getSize());
        media.setFileType(metaData.getMediaList().get(0).getContainer());
        return media;
    }
}
