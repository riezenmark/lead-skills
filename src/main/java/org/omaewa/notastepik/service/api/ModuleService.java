package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.Module;
import org.omaewa.notastepik.service.api.util.CrudService;

public interface ModuleService extends CrudService<Module, Long> {
    void deleteAllAnnouncementModules(final Long announcementId);
}
