package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.StoreConverter;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/")
@Validated
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}/stores")
    public ApiResponse<StoreResponseDTO.JoinResultDTO> join(@RequestBody @Valid StoreRequestDTO.JoinDto request,
                                                            @PathVariable Long regionId){
        Store store = storeCommandService.joinStore(request, regionId);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }
}
