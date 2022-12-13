package com.example.movieplatformpetproject.api;

import com.example.movieplatformpetproject.api.dto.MovieInputDto;
import com.example.movieplatformpetproject.api.dto.MovieOutputDto;
import com.example.movieplatformpetproject.api.dto.MovieOutputDtoConverter;
import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;
    private final MovieOutputDtoConverter movieOutputDtoConverter;


    @PostMapping
    public MovieOutputDto create(@RequestBody MovieInputDto movieInputDto) {
        Movie movie = movieService.create(movieInputDto);
        return movieOutputDtoConverter.convert(movie);
    }
}
