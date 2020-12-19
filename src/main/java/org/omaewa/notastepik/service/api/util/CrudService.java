package org.omaewa.notastepik.service.api.util;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> list();

    T getOne(final ID id);

    T add(final T obj);

    T update(final ID id, final T obj);

    void delete(final ID id);
}
