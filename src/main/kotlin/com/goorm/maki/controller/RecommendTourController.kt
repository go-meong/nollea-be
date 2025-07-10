package com.goorm.maki.controller

import com.goorm.maki.constant.CategoryEnum
import com.goorm.maki.domain.dto.RecommendTourListDTO
import com.goorm.maki.domain.dto.RecommendTourRequestDto
import com.goorm.maki.domain.dto.RecommendTourResponseDTO
import com.goorm.maki.domain.dto.ReviewRequestDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "야간관광명소 추천/리뷰", description = "야간관광명소 추천/리뷰 API")
@RestController
@RequestMapping("/api/v1/recommend-tour")
class RecommendTourController {
    @GetMapping
    @Operation(summary = "야간 관광명소 목록 추천 목록", description = "야간 관광명소를 추천받는하는 API입니다.")
    fun findRecommendTour(@ModelAttribute req: RecommendTourRequestDto): List<RecommendTourListDTO> = listOf(
        RecommendTourListDTO(
            id = ObjectId("64a8f1a2b7d2e3c111222333").toHexString(),
            categoryList = listOf(
                CategoryEnum.NIGHT_VIEW,
                CategoryEnum.NATURE,
                CategoryEnum.ROMANTIC
            ),
            fullAddress = "제주특별자치도 제주시 애월읍 곽지리 1359-1",
            title = "곽지해수욕장",
            coordinates = listOf(126.3912, 33.4561), // [longitude, latitude]
            serviceHours = listOf("18:00", "23:00")
        ),
        RecommendTourListDTO(
            id = ObjectId("64a8f1a2b7d2e3c111222334").toHexString(),
            categoryList = listOf(
                CategoryEnum.NATURE,
                CategoryEnum.WALKING_PATH
            ),
            fullAddress = "제주특별자치도 서귀포시 중문동 2624",
            title = "천제연폭포",
            coordinates = listOf(126.4125, 33.2459),
            serviceHours = listOf("09:00", "18:00")
        ),
        RecommendTourListDTO(
            id = ObjectId("64a8f1a2b7d2e3c111222335").toHexString(),
            categoryList = listOf(
                CategoryEnum.NIGHT_VIEW,
                CategoryEnum.ROMANTIC
            ),
            fullAddress = "제주특별자치도 제주시 조천읍 신북로 550",
            title = "에코랜드 전망대",
            coordinates = listOf(126.6547, 33.4785),
            serviceHours = listOf("17:00", "21:00")
        )
    )

    @GetMapping("/{placeId}")
    @Operation(summary = "야간 관광명소 목록 추천 상세", description = "야간 관광명소를 상세조회하는 API입니다.")
    fun getRecommendTour(@PathVariable placeId: String): RecommendTourResponseDTO =
        RecommendTourResponseDTO(
            id = placeId,
            title = "한라산 야경 명소",
            fullAddress = "제주특별자치도 제주시 한라산로 123",
            serviceHours = listOf("18:00~22:00", "주말 연장 운영"),
            description = "한라산 중턱에서 바라보는 환상적인 야경 명소입니다.",
            congestionLevel = "보통",
            lastBusInfo = "22:30 막차 / 780번",
            positiveRate = 87,
            negativeRate = 13,
            reviews = listOf(
                "정말 멋진 경치였어요!",
                "야경이 최고였고 접근성도 괜찮았어요.",
                "사람이 좀 많았지만 그만한 가치가 있네요."
            ),
            imageUrl = "https://example.com/images/hanla-night.jpg"
        )

    @PostMapping("/review")
    @Operation(summary = "리뷰 생성", description = "리뷰 생성 등록하는 API입니다.")
    fun createReview(@RequestBody req: ReviewRequestDTO): RecommendTourResponseDTO {
        return RecommendTourResponseDTO(
            id = req.id,
            title = "한라산 야경 명소",
            fullAddress = "제주특별자치도 제주시 한라산로 123",
            serviceHours = listOf("18:00~22:00", "주말 연장 운영"),
            description = "한라산 중턱에서 바라보는 환상적인 야경 명소입니다.",
            congestionLevel = "보통",
            lastBusInfo = "22:30 막차 / 780번",
            positiveRate = 87,
            negativeRate = 13,
            reviews = listOf(
                "정말 멋진 경치였어요!",
                "야경이 최고였고 접근성도 괜찮았어요.",
                "사람이 좀 많았지만 그만한 가치가 있네요.",
                req.review
            ),
            imageUrl = "https://example.com/images/hanla-night.jpg"
        )
    }


}

