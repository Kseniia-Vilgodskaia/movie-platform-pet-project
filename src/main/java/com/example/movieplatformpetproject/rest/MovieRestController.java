package com.example.movieplatformpetproject.rest;

import com.example.movieplatformpetproject.dto.MovieInputDto;
import com.example.movieplatformpetproject.dto.MovieOutputDto;
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

    @PostMapping
    public MovieOutputDto addMovie(@RequestBody MovieInputDto movieInputDto) {
        return movieService.create(movieInputDto);
    }
}
