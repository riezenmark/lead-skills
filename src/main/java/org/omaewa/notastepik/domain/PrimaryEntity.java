package org.omaewa.notastepik.domain;

public interface PrimaryEntity<ID> {
    ID getId();

    void setId(ID id);
}
