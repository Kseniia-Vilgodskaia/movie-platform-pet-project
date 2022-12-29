package com.vilgodskaia.movieplatformpetproject.api.movie;

import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieInputDto;
import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieOutputDto;
import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieOutputDtoConverter;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
    public Page<MovieOutputDto> getPage(@RequestParam(defaultValue = "0", required = false) Integer page,
                                        @RequestParam(defaultValue = "5", required = false) Integer size,
                                        @RequestParam(defaultValue = "year", required = false) String sort,
                                        @RequestParam(defaultValue = "ASC", required = false) Sort.Direction direction) {
        Page<Movie> movies = movieService.getPage(page, size, sort, direction);
        return movies.map(movieOutputDtoConverter::convert);
    }

    @GetMapping("/{id}")
    public MovieOutputDto get(@PathVariable UUID id) {
        Movie movie = movieService.get(id);
        return movieOutputDtoConverter.convert(movie);
    }

    @PutMapping("/{id}")
    public MovieOutputDto update(@PathVariable UUID id, @RequestBody MovieInputDto movieInputDto) {
        Movie movie = movieService.update(id, movieInputDto);
        return movieOutputDtoConverter.convert(movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        movieService.delete(id);
    }
}
