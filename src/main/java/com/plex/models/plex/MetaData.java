package com.plex.models.plex;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Model representation of a Plex Meta Data.
 */
@Data
public class MetaData {
    private String key;
    private String type;
    private String title;
    private String summary;
    @JsonProperty("Media")
    private List<Media> mediaList;
}
