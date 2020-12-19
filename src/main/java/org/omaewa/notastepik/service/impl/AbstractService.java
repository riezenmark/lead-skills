package org.omaewa.notastepik.service.impl;

import lombok.RequiredArgsConstructor;
import org.omaewa.notastepik.domain.PrimaryEntity;
import org.omaewa.notastepik.service.api.util.CrudService;
import org.omaewa.notastepik.service.api.util.FormattingService;
import org.omaewa.notastepik.service.api.util.ValidatingService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractService<ID, T extends PrimaryEntity<ID>, R extends JpaRepository<T, ID>> implements CrudService<T, ID>, ValidatingService<T>, FormattingService<ID, T> {
    protected final R repository;

    @Override
    @Transactional(readOnly = true)
    public Page<T> getPage(@PageableDefault final Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public T getOne(final ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public T add(final T obj) {
        return Optional.ofNullable(obj)
                .filter(this::isValid)
                .map(this::saveFormatted)
                .orElse(null);
    }

    @Override
    @Transactional
    public T update(final ID id, final T obj) {
        T objFromRepository = repository.findById(id).orElse(null);
        if (objFromRepository != null && obj != null && isValid(obj)) {
            BeanUtils.copyProperties(obj, objFromRepository, "id");
            repository.save(objFromRepository);
        }
        return objFromRepository;
    }

    @Override
    @Transactional
    public void delete(final ID id) {
        repository.deleteById(id);
    }

    @Override
    public void format(final T obj) {
        obj.setId(null);
    }

    @Transactional
    public T saveFormatted(final T obj) {
        format(obj);
        return repository.save(obj);
    }
}
