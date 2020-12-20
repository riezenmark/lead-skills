package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.domain.AnnouncementType;
import org.omaewa.notastepik.repository.AnnouncementRepository;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.service.api.ModuleService;
import org.omaewa.notastepik.service.api.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnnouncementServiceImpl extends AbstractService<Long, Announcement, AnnouncementRepository> implements AnnouncementService {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Lazy
    @Autowired
    private AnnouncementService _self;

    private final ReviewService reviewService;
    private final ModuleService moduleService;

    public AnnouncementServiceImpl(
            final AnnouncementRepository repository,
            final ReviewService reviewService,
            final ModuleService moduleService
    ) {
        super(repository);
        this.reviewService = reviewService;
        this.moduleService = moduleService;
    }

    @Override
    @Transactional
    public void deleteAllUserAnnouncements(final String userId) {
        Iterable<Announcement> announcementsOfUser = repository.findAllByUser_Id(userId);
        announcementsOfUser.forEach(this::delete);
        repository.deleteInBatch(announcementsOfUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Announcement> getAnnouncements(final String q, final AnnouncementType type) {
        List<Announcement> announcements;
        if (Objects.nonNull(q)) {
            announcements = repository.findByHeadingLike(q.toUpperCase());
        } else if (Objects.nonNull(type)) {
            announcements = repository.findByType(type);
        } else {
            announcements = repository.findAll();
        }
        return announcements;
    }

    @Override
    public List<Announcement> getAnnouncementsOfUserWithId(String userId) {
        return repository.findAllByUser_Id(userId);
    }

    private void delete(final Announcement announcement) {
        _self.delete(announcement.getId());
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        reviewService.deleteAllAnnouncementReviews(id);
        moduleService.deleteAllAnnouncementModules(id);
        repository.deleteById(id);
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
                && announcement.getRating() <= 5
                && Objects.nonNull(announcement.getAuthor())
                && Objects.nonNull(announcement.getType());
    }

    private boolean hasUniqueHeading(final Announcement announcement) {
        return !repository.existsByHeading(announcement.getHeading());
    }
}
