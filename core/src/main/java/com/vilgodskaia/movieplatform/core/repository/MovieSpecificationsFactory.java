package com.vilgodskaia.movieplatform.core.repository;

import com.vilgodskaia.movieplatform.core.api.movie.dto.MovieFilter;
import com.vilgodskaia.movieplatform.core.model.Movie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.vilgodskaia.movieplatform.core.util.PredicateUtils.likeIgnoreCase;

public class MovieSpecificationsFactory {

    public static Specification<Movie> filter(MovieFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(filter.getSearchString())) {
                Predicate[] searchStringPredicates = new Predicate[]{
                        likeIgnoreCase(criteriaBuilder, root, Movie.Fields.title, filter.getSearchString()),
                        likeIgnoreCase(criteriaBuilder, root, Movie.Fields.director, filter.getSearchString())
                };
                predicates.add(criteriaBuilder.or(searchStringPredicates));
            }
            if (filter.getYearFrom() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get(Movie.Fields.year), filter.getYearFrom()));
            }
            if (filter.getYearTo() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get(Movie.Fields.year), filter.getYearTo()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
