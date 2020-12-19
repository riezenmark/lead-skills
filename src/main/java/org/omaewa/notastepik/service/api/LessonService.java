package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.Lesson;
import org.omaewa.notastepik.service.api.util.CrudService;

public interface LessonService extends CrudService<Lesson, Long> {
    void deleteAllModuleLessons(final Long moduleId);
}
