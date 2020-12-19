package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.service.api.util.CrudService;

public interface AnnouncementService extends CrudService<Announcement, Long> {
    void deleteAllUserAnnouncements(final Long userId);

    void deleteAllAnnouncementsWithSubject(final Integer subjectId);
}
