package com.vilgodskaia.movieplatformpetproject.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PredicateUtils {
    public static <T> Predicate likeIgnoreCase(CriteriaBuilder criteriaBuilder, Root<T> root, String field, String searchString) {
        return criteriaBuilder.like(criteriaBuilder.upper(root.get(field)), "%" + searchString.toUpperCase() + "%");
    }
}
