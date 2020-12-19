package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Module;
import org.omaewa.notastepik.repository.ModuleRepository;
import org.omaewa.notastepik.service.api.LessonService;
import org.omaewa.notastepik.service.api.ModuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class ModuleServiceImpl extends AbstractService<Long, Module, ModuleRepository> implements ModuleService {
    private final LessonService lessonService;

    public ModuleServiceImpl(final ModuleRepository repository, final LessonService lessonService) {
        super(repository);
        this.lessonService = lessonService;
    }

    @Override
    public void delete(final Long id) {
        repository.findById(id).ifPresent(module -> {
            lessonService.deleteAllModuleLessons(module.getId());
            repository.delete(module);
        });
    }

    @Override
    @Transactional
    public void deleteAllAnnouncementModules(final Long announcementId) {
        Iterable<Module> announcementModules = repository.findAllByAnnouncement_Id(announcementId);
        announcementModules.forEach(module -> lessonService.deleteAllModuleLessons(module.getId()));
        repository.deleteInBatch(announcementModules);
    }

    @Override
    public boolean isValid(final Module module) {
        return StringUtils.hasLength(module.getName())
                && module.getName().length() <= 255
                && module.getNumber() >= 1
                && Objects.nonNull(module.getAnnouncement())
                && !repository.existsByNumberOrNameAndAnnouncement_Id(module.getNumber(), module.getName(), module.getAnnouncement().getId());
    }
}
