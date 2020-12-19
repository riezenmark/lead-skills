package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.service.api.util.CrudService;

import java.util.List;

public interface AnnouncementService extends CrudService<Announcement, Long> {
    void deleteAllUserAnnouncements(final String userId);

    List<Announcement> getAnnouncements(final String q);

    List<Announcement> getAnnouncementsOfUserWithId(final String userId);
}
