package com.ravenous.LuminaFind.review.impl;

import com.ravenous.LuminaFind.company.Company;
import com.ravenous.LuminaFind.company.CompanyService;
import com.ravenous.LuminaFind.review.Review;
import com.ravenous.LuminaFind.review.ReviewController;
import com.ravenous.LuminaFind.review.ReviewRepository;
import com.ravenous.LuminaFind.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = getAllReviews(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Review updatedReview, Long updatedReviewId) {
        if(companyService.getCompanyById(companyId) != null){
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(updatedReviewId);
            reviewRepository.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null
            && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public boolean updateReview(Long companyId, Review updatedReview, Long reviewId) {
//        List<Review> reviews = getAllReviews(companyId);
//        Optional<Review> reviewOptional = reviews.stream()
//                .filter(review -> review.getId().equals(reviewId))
//                .findFirst();
//
//        if(reviewOptional.isPresent()){
//            Review existingReview = reviewOptional.get();
//            existingReview.setId(updatedReview.getId());
//            existingReview.setDescription(updatedReview.getDescription());
//            existingReview.setTitle(updatedReview.getTitle());
//            existingReview.setRating(updatedReview.getRating());
//
//            reviewRepository.save(existingReview);
//
//            return true;
//        } else {
//            return false;
//        }
//    }


}
