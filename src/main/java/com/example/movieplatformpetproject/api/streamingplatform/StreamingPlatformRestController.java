package com.example.movieplatformpetproject.api.streamingplatform;

import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformInputDto;
import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputConverter;
import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputDto;
import com.example.movieplatformpetproject.model.StreamingPlatform;
import com.example.movieplatformpetproject.service.StreamingPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/streamings")
public class StreamingPlatformRestController {

    private final StreamingPlatformService streamingPlatformService;
    private final StreamingPlatformOutputConverter streamingPlatformOutputConverter;

    @PostMapping
    public StreamingPlatformOutputDto create(@RequestBody StreamingPlatformInputDto streamingPlatformInputDto) {
        StreamingPlatform streamingPlatform = streamingPlatformService.create(streamingPlatformInputDto);
        return streamingPlatformOutputConverter.convert(streamingPlatform);
    }
}
