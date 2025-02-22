package com.ravenous.LuminaFind.review;

import com.ravenous.LuminaFind.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllCompanies(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved){
            return new ResponseEntity<>("Review added successfully!!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review is not not added", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if(reviewId != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @RequestBody Review updatedReview, @PathVariable Long reviewId){
        boolean isReviewUpdated = reviewService.updateReview(companyId, updatedReview, reviewId);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully!!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review has not Updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted successfully!!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review has not deleted", HttpStatus.NOT_FOUND);
        }
    }



}
