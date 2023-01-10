package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform;

import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformCreateDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformOutputDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformOutputDtoConverter;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformUpdateDto;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.service.MovieOnStreamingPlatformService;
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
@RequestMapping("/movie-on-streamings")
@RequiredArgsConstructor
public class MovieOnStreamingPlatformRestController {

    private final MovieOnStreamingPlatformService movieOnStreamingPlatformService;
    private final MovieOnStreamingPlatformOutputDtoConverter movieOnStreamingPlatformOutputDtoConverter;

    @PostMapping
    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    public MovieOnStreamingPlatformOutputDto create(@RequestBody MovieOnStreamingPlatformCreateDto movieOnStreamingPlatformCreateDto) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = movieOnStreamingPlatformService.create(movieOnStreamingPlatformCreateDto);
        return movieOnStreamingPlatformOutputDtoConverter.convert(movieOnStreamingPlatform);
    }

    @GetMapping
    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_CLIENT)
    public Page<MovieOnStreamingPlatformOutputDto> getPage(@RequestParam(defaultValue = DEFAULT_PAGE, required = false) Integer page,
                                                           @RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
                                                           @RequestParam(defaultValue = "availableUntil", required = false) String sort,
                                                           @RequestParam(defaultValue = DEFAULT_SORT_DIRECTION, required = false) Sort.Direction direction) {
        return movieOnStreamingPlatformService
                .getPage(page, size, sort, direction)
                .map(movieOnStreamingPlatformOutputDtoConverter::convert);
    }

    @GetMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_ADMIN_OR_CLIENT)
    public MovieOnStreamingPlatformOutputDto get(@PathVariable UUID id) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = movieOnStreamingPlatformService.get(id);
        return movieOnStreamingPlatformOutputDtoConverter.convert(movieOnStreamingPlatform);
    }

    @PutMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    public MovieOnStreamingPlatformOutputDto update(@PathVariable UUID id, @RequestBody MovieOnStreamingPlatformUpdateDto movieOnStreamingPlatformUpdateDto) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = movieOnStreamingPlatformService.update(id, movieOnStreamingPlatformUpdateDto);
        return movieOnStreamingPlatformOutputDtoConverter.convert(movieOnStreamingPlatform);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(HAS_AUTHORITY_ADMIN)
    public void delete(@PathVariable UUID id) {
        movieOnStreamingPlatformService.delete(id);
    }
}
