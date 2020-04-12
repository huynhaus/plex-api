package com.plex.server.models;

import lombok.Data;

/**
 * Model representation of a Plex Directory.
 */
@Data
public class Directory {
    private int key;
    private String type;
    private String title;
    private String thumb;
}
