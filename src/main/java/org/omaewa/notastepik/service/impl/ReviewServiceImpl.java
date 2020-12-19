package org.omaewa.notastepik.service.impl;

import org.omaewa.notastepik.domain.Review;
import org.omaewa.notastepik.repository.ReviewRepository;
import org.omaewa.notastepik.service.api.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class ReviewServiceImpl extends AbstractService<Long, Review, ReviewRepository> implements ReviewService {
    public ReviewServiceImpl(ReviewRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public void deleteAllAnnouncementReviews(final Long announcementId) {
        Iterable<Review> announcementReviews = repository.findAllByAnnouncement_Id(announcementId);
        repository.deleteInBatch(announcementReviews);
    }

    @Override
    @Transactional
    public void deleteAllUserReviews(final String userId) {
        Iterable<Review> usersReviews = repository.findAllByUser_Id(userId);
        repository.deleteInBatch(usersReviews);
    }

    @Override
    public boolean isValid(final Review review) {
        return review.getEvaluation() >= 1
                && review.getEvaluation() <= 5
                && Objects.nonNull(review.getUser())
                && (!StringUtils.hasLength(review.getComment()) || review.getComment().length() <= 255)
                && Objects.nonNull(review.getAnnouncement());
    }
}
