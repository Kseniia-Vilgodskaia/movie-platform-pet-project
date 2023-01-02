package com.vilgodskaia.movieplatformpetproject.api.streamingplatform;

import com.vilgodskaia.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformInputDto;
import com.vilgodskaia.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputConverter;
import com.vilgodskaia.movieplatformpetproject.api.streamingplatform.dto.StreamingPlatformOutputDto;
import com.vilgodskaia.movieplatformpetproject.model.StreamingPlatform;
import com.vilgodskaia.movieplatformpetproject.service.StreamingPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.vilgodskaia.movieplatformpetproject.util.PaginationUtil.*;

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
    public Page<StreamingPlatformOutputDto> getPage(@RequestParam(defaultValue = DEFAULT_PAGE, required = false) Integer page,
                                                    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE, required = false) Integer size,
                                                    @RequestParam(defaultValue = "name", required = false) String sort,
                                                    @RequestParam(defaultValue = DEFAULT_SORT_DIRECTION, required = false) Sort.Direction direction) {
        return streamingPlatformService.getPage(page, size, sort, direction)
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
