package org.omaewa.notastepik.service.api;

import org.omaewa.notastepik.domain.Review;
import org.omaewa.notastepik.service.api.util.CrudService;

public interface ReviewService extends CrudService<Review, Long> {
    void deleteAllAnnouncementReviews(final Long announcementId);

    void deleteAllUserReviews(final String userId);
}
