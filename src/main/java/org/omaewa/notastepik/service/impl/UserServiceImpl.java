package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Role;
import org.omaewa.notastepik.domain.User;
import org.omaewa.notastepik.repository.UserRepository;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.service.api.ReviewService;
import org.omaewa.notastepik.service.api.TimetableService;
import org.omaewa.notastepik.service.api.UserService;
import org.omaewa.notastepik.service.api.util.PasswordEmailValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserServiceImpl extends AbstractService<String, User, UserRepository> implements UserService {
    private final AnnouncementService announcementService;
    private final ReviewService reviewService;
    private final TimetableService timetableService;

    public UserServiceImpl(
            final UserRepository repository,
            final AnnouncementService announcementService,
            final ReviewService reviewService,
            final TimetableService timetableService
    ) {
        super(repository);
        this.announcementService = announcementService;
        this.reviewService = reviewService;
        this.timetableService = timetableService;
    }

    @Override
    @Transactional
    public void delete(final String id) {
        repository.findById(id).ifPresent(user -> {
            announcementService.deleteAllUserAnnouncements(user.getId());
            timetableService.deleteAllUserTimetables(user.getId());
            reviewService.deleteAllUserReviews(user.getId());
            repository.delete(user);
        });
    }

    @Override
    public final boolean isValid(final User user) {
        return hasValidFields(user)
                && hasUniqueFields(user);
    }

    private boolean hasValidFields(final User user) {
        return hasRequiredFields(user)
                && user.getUsername().length() <= 31
                && user.getEmail().length() <= 255
                && PasswordEmailValidator.emailIsValid(user.getEmail())
                && !user.getAuthorities().contains(Role.ADMIN);
    }

    private boolean hasRequiredFields(final User user) {
        return StringUtils.hasLength(user.getUsername())
                && StringUtils.hasLength(user.getEmail())
                && Objects.nonNull(user.getAuthorities())
                && !user.getAuthorities().isEmpty();
    }

    private boolean hasUniqueFields(final User user) {
        return !repository.existsByUsernameOrEmail(user.getUsername(), user.getEmail());
    }

    @Override
    public void format(final User user) {
        super.format(user);
        user.setEnabled(false);
    }
}
