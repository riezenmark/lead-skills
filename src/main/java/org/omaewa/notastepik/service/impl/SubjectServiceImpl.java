package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Subject;
import org.omaewa.notastepik.repository.SubjectRepository;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.service.api.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class SubjectServiceImpl extends AbstractService<Integer, Subject, SubjectRepository> implements SubjectService {
    private final AnnouncementService announcementService;

    public SubjectServiceImpl(final SubjectRepository repository, final AnnouncementService announcementService) {
        super(repository);
        this.announcementService = announcementService;
    }

    @Override
    @Transactional
    public void delete(final Integer id) {
        repository.findById(id).ifPresent(subject -> {
            announcementService.deleteAllAnnouncementsWithSubject(subject.getId());
            repository.delete(subject);
        });
    }

    @Override
    public final boolean isValid(final Subject subject) {
        return StringUtils.hasLength(subject.getName())
                && subject.getName().length() <= 255
                && !repository.existsByName(subject.getName());
    }
}
