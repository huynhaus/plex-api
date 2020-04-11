package com.plex.album;

import lombok.Data;

/**
 * Model representation of an album.
 */
@Data
public class Album {
    private int id;
    private String title;
    private String thumbnailUrl;
}
