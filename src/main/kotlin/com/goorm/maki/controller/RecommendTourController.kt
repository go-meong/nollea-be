package com.goorm.maki.controller

import com.goorm.maki.domain.dto.RecommendTourListDTO
import com.goorm.maki.domain.dto.RecommendTourRequestDto
import com.goorm.maki.domain.dto.ReviewRequestDTO
import com.goorm.maki.service.RecommendTourService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "야간관광명소 추천/리뷰", description = "야간관광명소 추천/리뷰 API")
@RestController
@RequestMapping("/api/v1/recommend-tour")
class RecommendTourController(
    private val service: RecommendTourService
) {
    @GetMapping
    @Operation(summary = "야간 관광명소 목록 추천 목록", description = "야간 관광명소를 추천받는하는 API입니다.")
    fun findRecommendTour(@ModelAttribute recommendTourRequestDto: RecommendTourRequestDto): List<RecommendTourListDTO> {
        return service.findRecommendTour(recommendTourRequestDto)
    }

    @PostMapping("/review")
    @Operation(summary = "리뷰 생성", description = "리뷰 생성 등록하는 API입니다.")
    fun createReview(@RequestBody req: ReviewRequestDTO): Boolean {
        return service.createReview(req)
    }


}

