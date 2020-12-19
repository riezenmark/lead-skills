package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Role;
import org.omaewa.notastepik.domain.User;
import org.omaewa.notastepik.repository.UserRepository;
import org.omaewa.notastepik.service.api.AnnouncementService;
import org.omaewa.notastepik.service.api.ReviewService;
import org.omaewa.notastepik.service.api.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserServiceImpl extends AbstractService<Long, User, UserRepository> implements UserService {
    private static final String PASSWORD_REGEX;
    private static final String EMAIL_REGEX;

    static {
        PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,31}$";
        EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    }

    private final AnnouncementService announcementService;
    private final ReviewService reviewService;

    public UserServiceImpl(final UserRepository repository, final AnnouncementService announcementService, final ReviewService reviewService) {
        super(repository);
        this.announcementService = announcementService;
        this.reviewService = reviewService;
    }

    @Override
    @Transactional
    public void delete(final Long id) {
        repository.findById(id).ifPresent(user -> {
            announcementService.deleteAllUserAnnouncements(user.getId());
            reviewService.deleteAllUserReviews(user.getId());
            repository.delete(user);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    @Override
    public final boolean isValid(final User user) {
        return hasValidFields(user)
                && hasUniqueFields(user);
    }

    private boolean hasValidFields(final User user) {
        return hasRequiredFields(user)
                && user.getName().length() <= 31
                && user.getSurname().length() <= 31
                && user.getUsername().length() <= 31
                && user.getRating() >= 0
                && user.getRating() <= 10
                && user.getPassword().matches(PASSWORD_REGEX)
                && user.getEmail().length() <= 255
                && user.getEmail().matches(EMAIL_REGEX)
                && !user.getAuthorities().contains(Role.ADMIN);
    }

    private boolean hasRequiredFields(final User user) {
        return StringUtils.hasLength(user.getName())
                && StringUtils.hasLength(user.getSurname())
                && StringUtils.hasLength(user.getUsername())
                && StringUtils.hasLength(user.getPassword())
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
        user.setRating((short) 5);
        user.setEnabled(false);
    }
}
