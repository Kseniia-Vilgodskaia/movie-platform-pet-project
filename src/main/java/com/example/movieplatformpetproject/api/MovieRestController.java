package com.example.movieplatformpetproject.api;

import com.example.movieplatformpetproject.api.dto.MovieInputDto;
import com.example.movieplatformpetproject.api.dto.MovieOutputDto;
import com.example.movieplatformpetproject.api.dto.MovieOutputDtoConverter;
import com.example.movieplatformpetproject.model.Movie;
import com.example.movieplatformpetproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<MovieOutputDto> findAll() {
        List<Movie> movies = movieService.findAll();
        return movies.stream()
                .map(movieOutputDtoConverter::convert)
                .toList();
    }

    @GetMapping("/{id}")
    public MovieOutputDto findById(@PathVariable UUID id) {
        Movie movie = movieService.findById(id);
        return movieOutputDtoConverter.convert(movie);
    }
}
