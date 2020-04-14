package com.plex.models.api;

import lombok.Data;

import java.util.List;

/**
 * Model representation of an album.
 */
@Data
public class Album {
    private int id;
    private String title;
    private String type;
    private String coverImageUrl;
    private List<Media> mediaList;
}
