package com.vilgodskaia.movieplatformpetproject.api.movie.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieFilter {

    private String searchString;
    private Integer yearFrom;
    private Integer yearTo;
}
