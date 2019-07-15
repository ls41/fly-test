package com.lsy.common.rest;

import com.lsy.common.domain.AbstractAuditingEntity;
import com.lsy.common.service.AbstractService;

import java.util.List;

public abstract class AbstractRest<T extends AbstractAuditingEntity> {
    public abstract AbstractService<T> getService();

    public T create(T t) {
        return this.getService().create(t);
    }

    public T update(T t) {
        return this.getService().create(t);
    }

    public void delete(T t) {
        this.getService().delete(t);
    }

    public List<T> find(T t) {
        return this.getService().find(t);
    }

    public abstract T findById(Long id);
}
