package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.domain.AnnouncementType;
import org.omaewa.notastepik.service.api.util.CrudService;

import java.util.List;

public interface AnnouncementService extends CrudService<Announcement, Long> {
    void deleteAllUserAnnouncements(final Long userId);

    List<Announcement> getAnnouncements(final String q, final Long timeFrom, final Long timeTo, final AnnouncementType type);

    List<Announcement> getAnnouncementsOfUserWithId(final Long userId);
}
