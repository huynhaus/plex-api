package com.plex.models.plex;

import lombok.Data;

/**
 * Model representation of a Plex Part.
 */
@Data
public class Part {
    private int id;
    private String key;
    private int size;
}
