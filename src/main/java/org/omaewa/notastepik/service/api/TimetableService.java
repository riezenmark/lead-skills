package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.Timetable;
import org.omaewa.notastepik.service.api.util.CrudService;

public interface TimetableService extends CrudService<Timetable, Long> {
    void deleteAllUserTimetables(final String userId);
}
