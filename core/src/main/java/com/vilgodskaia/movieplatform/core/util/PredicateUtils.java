package com.vilgodskaia.movieplatform.core.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PredicateUtils {
    public static <T> Predicate likeIgnoreCase(CriteriaBuilder criteriaBuilder, Root<T> root, String field, String searchString) {
        return criteriaBuilder.like(criteriaBuilder.upper(root.get(field)), "%" + searchString.toUpperCase() + "%");
    }
}
