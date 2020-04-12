package com.plex.models.api;

import lombok.Data;

/**
 * Model representation of a media.
 */
@Data
public class Media {
    private int id;
    private String title;
    private String summary;
    private String type;
    private String url;
    private int width;
    private int height;
    private String aspectRatio;
    private int size;
    private String fileType;
}
