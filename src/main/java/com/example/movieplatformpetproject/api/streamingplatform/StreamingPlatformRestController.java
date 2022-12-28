package com.example.movieplatformpetproject.api.streamingplatform;

import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformInputDto;
import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputConverter;
import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputDto;
import com.example.movieplatformpetproject.model.StreamingPlatform;
import com.example.movieplatformpetproject.service.StreamingPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<StreamingPlatformOutputDto> getAll() {
        return streamingPlatformService.getAll()
                .stream()
                .map(streamingPlatformOutputConverter::convert)
                .toList();
    }

    @GetMapping("/{id}")
    public StreamingPlatformOutputDto get(@PathVariable UUID id) {
        StreamingPlatform streamingPlatform = streamingPlatformService.get(id);
        return streamingPlatformOutputConverter.convert(streamingPlatform);
    }

    @PutMapping("/{id}")
    public StreamingPlatformOutputDto update(@PathVariable UUID id, @RequestBody StreamingPlatformInputDto streamingPlatformInputDto) {
        StreamingPlatform streamingPlatform = streamingPlatformService.update(id, streamingPlatformInputDto);
        return streamingPlatformOutputConverter.convert(streamingPlatform);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        streamingPlatformService.delete(id);
    }
}
