package org.omaewa.notastepik.service.api.util;

import org.omaewa.notastepik.domain.PrimaryEntity;

public interface FormattingService<ID, T extends PrimaryEntity<ID>> {
    void format(T obj);
}
