package com.vilgodskaia.movieplatform.core.repository;

import com.vilgodskaia.movieplatform.core.api.streamingplatform.dto.StreamingPlatformFilter;
import com.vilgodskaia.movieplatform.core.model.StreamingPlatform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.vilgodskaia.movieplatform.core.util.PredicateUtils.likeIgnoreCase;

public class StreamingPlatformSpecificationsFactory {
    public static Specification<StreamingPlatform> filter(StreamingPlatformFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(filter.getSearchString())) {
                predicates.add(likeIgnoreCase(criteriaBuilder, root, StreamingPlatform.Fields.name, filter.getSearchString()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
