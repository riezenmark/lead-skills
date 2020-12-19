package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.omaewa.notastepik.service.api.util.CrudService;
import lombok.RequiredArgsConstructor;
import org.omaewa.notastepik.util.Views;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public abstract class AbstractRestController<T, ID, S extends CrudService<T, ID>> {
    protected final S service;

    @GetMapping
    @JsonView(Views.IdRepresentation.class)
    public Page<T> list(@PageableDefault final Pageable pageable) {
        return service.getPage(pageable);
    }

    @GetMapping("{id}")
    @JsonView(Views.IdRepresentation.class)
    public T getOne(@PathVariable("id") final ID id) {
        return service.getOne(id);
    }

    @PostMapping
    public T add(@RequestBody final T obj) {
        return service.add(obj);
    }

    @PutMapping("{id}")
    public T update(@PathVariable("id") final ID id, @RequestBody final T obj) {
        return service.update(id, obj);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") final ID id) {
        service.delete(id);
    }
}
