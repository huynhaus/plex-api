package com.plex.library;

import com.plex.models.api.Album;
import com.plex.models.api.Library;
import com.plex.models.api.Media;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 *
 * Media title: "cover"
 */
@Service
public class MediaTitleProcessor {

    private static final String MEDIA_COVER_TITLE = "cover";

    public void process(Library library) {
        for (Album album: library.getAlbums()) {
            this.sort(album);
        }
    }

    private void sort(Album album) {
        for (Media media: album.getMediaList()) {
            if (StringUtils.contains(media.getTitle(), MEDIA_COVER_TITLE)) {
                album.setCoverImageUrl("/albums/" + album.getId() + "/medias/" + media.getId());
            }
        }
    }
}
