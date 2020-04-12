package com.plex.models.api;

import lombok.Data;

import java.util.List;

/**
 * Model representation of a library.
 */
@Data
public class Library {
    List<Album> albums;
}
