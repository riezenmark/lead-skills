package org.omaewa.notastepik.service.api.util;

public interface ValidatingService<T> {
    boolean isValid(final T obj);
}
