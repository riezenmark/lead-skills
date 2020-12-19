package org.omaewa.notastepik.repository.custom.api;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.domain.AnnouncementType;

import java.util.List;

public interface CustomAnnouncementRepository {
    List<Announcement> findAnnouncementsByParameters(final String q, final Long timeFrom, final Long timeTo, final AnnouncementType type);
}
