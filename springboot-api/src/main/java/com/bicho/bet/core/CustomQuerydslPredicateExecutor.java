package com.bicho.bet.core;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {
    @Override
    List<T> findAll(Predicate predicate);
    Page<T> findAll(Predicate predicate, Pageable pageable);

    default List<T> findAll(String filter, Class<T> entityType) {
        return this.findAll(BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType));
    }

    default Page<T> findAll(String filter, Class<T> entityType, Pageable pageable) {
        return this.findAll(BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType), pageable);
    }
}
