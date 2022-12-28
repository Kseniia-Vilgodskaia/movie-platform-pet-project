package com.example.movieplatformpetproject.api.streamingplatform;

import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformInputDto;
import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputConverter;
import com.example.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputDto;
import com.example.movieplatformpetproject.model.StreamingPlatform;
import com.example.movieplatformpetproject.service.StreamingPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
    public Page<StreamingPlatformOutputDto> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                   @RequestParam(defaultValue = "3", required = false) Integer size,
                                                   @RequestParam(defaultValue = "name", required = false) String sort,
                                                   @RequestParam(defaultValue = "ASC", required = false) Sort.Direction direction) {
        return streamingPlatformService.getAll(page, size, sort, direction)
                .map(streamingPlatformOutputConverter::convert);
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
