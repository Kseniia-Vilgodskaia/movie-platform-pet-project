package com.vilgodskaia.movieplatformpetproject.repository;

import com.vilgodskaia.movieplatformpetproject.api.movie.dto.MovieFilter;
import com.vilgodskaia.movieplatformpetproject.model.Movie;
import com.vilgodskaia.movieplatformpetproject.model.Movie_;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.vilgodskaia.movieplatformpetproject.util.PredicateUtils.likeIgnoreCase;

public class MovieSpecificationsFactory {

    public static Specification<Movie> filter(MovieFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(filter.getSearchString())) {
                Predicate[] searchStringPredicates = new Predicate[]{
                        likeIgnoreCase(criteriaBuilder, root, Movie_.TITLE, filter.getSearchString()),
                        likeIgnoreCase(criteriaBuilder, root, Movie_.DIRECTOR, filter.getSearchString())
                };
                predicates.add(criteriaBuilder.or(searchStringPredicates));
            }
            if (filter.getYearFrom() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get(Movie_.YEAR), filter.getYearFrom()));
            }
            if (filter.getYearTo() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get(Movie_.YEAR), filter.getYearTo()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
