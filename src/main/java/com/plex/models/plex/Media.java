package com.plex.models.plex;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Model representation of a Plex Media.
 */
@Data
public class Media {
    private int id;
    private int width;
    private int height;
    private String aspectRatio;
    private String container;
    @JsonProperty("Part")
    private List<Part> parts;
}
