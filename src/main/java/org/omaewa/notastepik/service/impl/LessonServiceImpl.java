package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Lesson;
import org.omaewa.notastepik.repository.LessonRepository;
import org.omaewa.notastepik.service.api.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class LessonServiceImpl extends AbstractService<Long, Lesson, LessonRepository> implements LessonService {
    public LessonServiceImpl(final LessonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void deleteAllModuleLessons(final Long moduleId) {
        Iterable<Lesson> moduleLessons = repository.findAllByModule_Id(moduleId);
        repository.deleteInBatch(moduleLessons);
    }

    @Override
    public boolean isValid(final Lesson lesson) {
        return lesson.getNumber() >= 1
                && StringUtils.hasLength(lesson.getName())
                && lesson.getName().length() <= 255
                && StringUtils.hasLength(lesson.getDescription())
                && Objects.nonNull(lesson.getModule())
                && repository.existsByNumberOrNameAndModule_Id(lesson.getNumber(), lesson.getName(), lesson.getModule().getId());
    }
}
