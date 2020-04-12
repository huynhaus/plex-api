package com.plex.models.plex;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * The root element of a Plex media container.
 */
@Data
public class MediaContainer {
    @JsonProperty("Directory")
    List<Directory> directories;

    @JsonProperty("Metadata")
    List<MetaData> metaDataList;
}
