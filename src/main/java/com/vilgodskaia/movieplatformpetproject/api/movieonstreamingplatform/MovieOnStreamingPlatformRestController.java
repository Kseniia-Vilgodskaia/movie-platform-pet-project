package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform;

import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformCreateDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformOutputDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformOutputDtoConverter;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformUpdateDto;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.service.MovieOnStreamingPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie-on-streamings")
@RequiredArgsConstructor
public class MovieOnStreamingPlatformRestController {

    private final MovieOnStreamingPlatformService movieOnStreamingPlatformService;
    private final MovieOnStreamingPlatformOutputDtoConverter movieOnStreamingPlatformOutputDtoConverter;

    @PostMapping
    public MovieOnStreamingPlatformOutputDto create(@RequestBody MovieOnStreamingPlatformCreateDto movieOnStreamingPlatformCreateDto) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = movieOnStreamingPlatformService.create(movieOnStreamingPlatformCreateDto);
        return movieOnStreamingPlatformOutputDtoConverter.convert(movieOnStreamingPlatform);
    }

    @GetMapping
    public List<MovieOnStreamingPlatformOutputDto> getAll() {
        return movieOnStreamingPlatformService
                .getAll()
                .stream()
                .map(movieOnStreamingPlatformOutputDtoConverter::convert)
                .toList();
    }

    @GetMapping("/{id}")
    public MovieOnStreamingPlatformOutputDto get(@PathVariable UUID id) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = movieOnStreamingPlatformService.get(id);
        return movieOnStreamingPlatformOutputDtoConverter.convert(movieOnStreamingPlatform);
    }

    @PutMapping("/{id}")
    public MovieOnStreamingPlatformOutputDto update(@PathVariable UUID id, @RequestBody MovieOnStreamingPlatformUpdateDto movieOnStreamingPlatformUpdateDto) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = movieOnStreamingPlatformService.update(id, movieOnStreamingPlatformUpdateDto);
        return movieOnStreamingPlatformOutputDtoConverter.convert(movieOnStreamingPlatform);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        movieOnStreamingPlatformService.delete(id);
    }
}
