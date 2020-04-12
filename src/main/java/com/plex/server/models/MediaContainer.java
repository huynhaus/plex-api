package com.plex.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * The root element of all Plex responses.
 */
@Data
public class MediaContainer {
    @JsonProperty("Directory")
    List<Directory> directories;
}
