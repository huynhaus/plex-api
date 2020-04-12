package com.plex.server.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Model representation of any response that Plex will return.
 */
@Data
public class PlexResponse {
    @JsonProperty("MediaContainer")
    private MediaContainer mediaContainer;
}
