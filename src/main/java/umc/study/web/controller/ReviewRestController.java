package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.validation.annotation.ExistStores;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;



    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.JoinResultDTO> join(@RequestBody @Valid ReviewRequestDTO.JoinDto request,
                                                             @PathVariable @ExistStores Long storeId){

        Review review = reviewCommandService.joinReview(request, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }


}
