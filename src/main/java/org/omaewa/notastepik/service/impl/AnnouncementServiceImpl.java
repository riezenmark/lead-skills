package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.repository.AnnouncementRepository;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.service.api.ModuleService;
import org.omaewa.notastepik.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class AnnouncementServiceImpl extends AbstractService<Long, Announcement, AnnouncementRepository> implements AnnouncementService {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Lazy
    @Autowired
    private AnnouncementService _self;
    private final ReviewService reviewService;
    private final ModuleService moduleService;

    public AnnouncementServiceImpl(final AnnouncementRepository repository, final ReviewService reviewService, final ModuleService moduleService) {
        super(repository);
        this.reviewService = reviewService;
        this.moduleService = moduleService;
    }

    @Override
    @Transactional
    public void deleteAllUserAnnouncements(final Long userId) {
        Iterable<Announcement> announcementsOfUser = repository.findAllByUser_Id(userId);
        announcementsOfUser.forEach(this::delete);
        repository.deleteInBatch(announcementsOfUser);
    }

    @Override
    @Transactional
    public void deleteAllAnnouncementsWithSubject(final Integer subjectId) {
        Iterable<Announcement> announcementsWithSubject = repository.findAllBySubject_Id(subjectId);
        announcementsWithSubject.forEach(this::delete);
        repository.deleteInBatch(announcementsWithSubject);
    }

    private void delete(final Announcement announcement) {
        _self.delete(announcement.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        reviewService.deleteAllAnnouncementReviews(id);
        moduleService.deleteAllAnnouncementModules(id);
    }

    @Override
    public boolean isValid(final Announcement announcement) {
        return hasRequiredFields(announcement)
                && hasUniqueHeading(announcement);
    }

    private boolean hasRequiredFields(final Announcement announcement) {
        return StringUtils.hasLength(announcement.getHeading())
                && announcement.getHeading().length() <= 255
                && StringUtils.hasLength(announcement.getDescription())
                && announcement.getRating() >= 0
                && announcement.getRating() <= 10
                && Objects.nonNull(announcement.getAuthor())
                && Objects.nonNull(announcement.getSubject())
                && Objects.nonNull(announcement.getType());
    }

    private boolean hasUniqueHeading(final Announcement announcement) {
        return !repository.existsByHeading(announcement.getHeading());
    }
}
