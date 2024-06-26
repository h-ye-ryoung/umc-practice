package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.ReviewConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.mapping.UserMission;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistStores;
import umc.study.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/")
@Validated
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping("/{userId}/missions/in-progress")
    @Operation(summary = "유저가 진행 중인 미션 목록 조회 API", description = "특정 유저가 진행 중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getUserInProgressMissions(@PathVariable(name = "userId") Long userId,
                                                                                           @CheckPage @RequestParam(name = "page") Integer page) {
        Page<UserMission> userMissionPage = storeQueryService.getUserInProgressMissions(userId, page - 1);
        MissionResponseDTO.MissionPreViewListDTO missionListDTO = MissionConverter.userMissionPreViewListDTO(userMissionPage);
        return ApiResponse.onSuccess(missionListDTO);
    }



    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getStoreMissionList(@ExistStores @PathVariable(name = "storeId") Long storeId,
                                                                                @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionPage = storeQueryService.getStoreMissionList(storeId, page - 1);
        MissionResponseDTO.MissionPreViewListDTO missionListDTO = MissionConverter.missionPreViewListDTO(missionPage);
        return ApiResponse.onSuccess(missionListDTO);
    }




    // ---

    @PostMapping("/missions")
    public ApiResponse<MissionResponseDTO.JoinResultDTO> join(@RequestBody @Valid MissionRequestDTO.JoinDto request){

        Mission mission = missionCommandService.joinMission(request);
        return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(mission));
    }

    @PostMapping("/missions/challenge")
    public ApiResponse<MissionResponseDTO.ChallengeResultDTO> challenge(@RequestBody @Valid MissionRequestDTO.ChallengeDTO request) {
        UserMission userMission = missionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MissionConverter.toChallengeResultDTO(userMission));
    }

}


