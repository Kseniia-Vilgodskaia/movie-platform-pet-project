package com.vilgodskaia.movieplatformpetproject.api.movie;

import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieInputDto;
import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieOutputDto;
import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieOutputDtoConverter;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.vilgodskaia.movieplatformpetproject.util.PaginationUtil.*;
import static com.vilgodskaia.movieplatformpetproject.util.SecurityUtil.HAS_AUTHORITY_ADMIN;
import static com.vilgodskaia.movieplatformpetproject.util.SecurityUtil.HAS_AUTHORITY_ADMIN_OR_CLIENT;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;
    private final MovieOutputDtoConverter movieOutputDtoConverter;


    @PostMapping
    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_CLIENT)
    public MovieOutputDto create(@RequestBody MovieInputDto movieInputDto) {
        Movie movie = movieService.create(movieInputDto);
        return movieOutputDtoConverter.convert(movie);
    }

    @GetMapping
    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_CLIENT)
    public Page<MovieOutputDto> getPage(@RequestParam(defaultValue = DEFAULT_PAGE, required = false) Integer page,
                                        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
                                        @RequestParam(defaultValue = "year", required = false) String sort,
                                        @RequestParam(defaultValue = DEFAULT_SORT_DIRECTION, required = false) Sort.Direction direction) {
        Page<Movie> movies = movieService.getPage(page, size, sort, direction);
        return movies.map(movieOutputDtoConverter::convert);
    }

    @GetMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_CLIENT)
    public MovieOutputDto get(@PathVariable UUID id) {
        Movie movie = movieService.get(id);
        return movieOutputDtoConverter.convert(movie);
    }

    @PutMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    public MovieOutputDto update(@PathVariable UUID id, @RequestBody MovieInputDto movieInputDto) {
        Movie movie = movieService.update(id, movieInputDto);
        return movieOutputDtoConverter.convert(movie);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    public void delete(@PathVariable UUID id) {
        movieService.delete(id);
    }
}
