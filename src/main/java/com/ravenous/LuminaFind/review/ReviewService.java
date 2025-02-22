package com.ravenous.LuminaFind.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Review review, Long updatedReviewId);

    boolean deleteReview(Long companyId, Long reviewId);
}
