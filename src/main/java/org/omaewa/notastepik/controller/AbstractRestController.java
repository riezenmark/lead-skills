package org.omaewa.notastepik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.omaewa.notastepik.service.api.util.CrudService;
import org.omaewa.notastepik.util.Views;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractRestController<T, ID, S extends CrudService<T, ID>> {
    protected final S service;

    @GetMapping
    @JsonView(Views.IdRepresentation.class)
    public List<T> list() {
        return service.list();
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
