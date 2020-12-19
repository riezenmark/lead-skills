package org.omaewa.notastepik.service.api.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface CrudService<T, ID> {
    Page<T> getPage(@PageableDefault final Pageable pageable);

    T getOne(final ID id);

    T add(final T obj);

    T update(final ID id, final T obj);

    void delete(final ID id);
}
