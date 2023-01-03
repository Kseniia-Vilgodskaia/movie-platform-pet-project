package com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform;

import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformInputDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformOutputDto;
import com.vilgodskaia.movieplatformpetproject.api.movieonstreamingplatform.dto.MovieOnStreamingPlatformOutputDtoConverter;
import com.vilgodskaia.movieplatformpetproject.model.MovieOnStreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.service.MovieOnStreamingPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-on-streamings")
@RequiredArgsConstructor
public class MovieOnStreamingPlatformRestController {

    private final MovieOnStreamingPlatformService movieOnStreamingPlatformService;
    private final MovieOnStreamingPlatformOutputDtoConverter movieOnStreamingPlatformOutputDtoConverter;

    @PostMapping
    public MovieOnStreamingPlatformOutputDto create(@RequestBody MovieOnStreamingPlatformInputDto movieOnStreamingPlatformInputDto) {
        MovieOnStreamingPlatform movieOnStreamingPlatform = movieOnStreamingPlatformService.create(movieOnStreamingPlatformInputDto);
        return movieOnStreamingPlatformOutputDtoConverter.convert(movieOnStreamingPlatform);
    }
}
