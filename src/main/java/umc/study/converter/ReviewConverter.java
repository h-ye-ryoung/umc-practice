package umc.study.converter;

import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static Review toReview (ReviewRequestDTO.JoinDto request, Store store, User user) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .store(store)
                .user(user)
                .build();
    }

    public static ReviewResponseDTO.JoinResultDTO toJoinResultDTO(Review review){
        return ReviewResponseDTO.JoinResultDTO.builder()
                .reviewId(review.getReview_id())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
