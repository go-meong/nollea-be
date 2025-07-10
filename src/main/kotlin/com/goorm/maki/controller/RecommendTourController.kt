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
            recommendReason = "가족과 함께하는 여행에서는 다양한 먹거리를 즐길 수 있는 장소가 아주 중요하죠! 제주 동문시장은 퓨전 먹거리와 다채로운 쇼 공연이 어우러져 있어, 아이들과 함께 즐거운 시간을 보내기에 최적의 장소입니다. 또한, 활기찬 분위기 속에서 가족이 함께 소중한 추억을 만들 수 있는 기회를 제공합니다.",
            coordinates = listOf(126.3912, 33.4561), // [longitude, latitude]
            serviceHours = listOf("18:00", "23:00"),
            reviews = TODO(),
            imageUrl = TODO()
        ),
        RecommendTourListDTO(
            id = ObjectId("64a8f1a2b7d2e3c111222334").toHexString(),
            categoryList = listOf(
                CategoryEnum.NATURE,
                CategoryEnum.WALKING_PATH
            ),
            fullAddress = "제주특별자치도 서귀포시 중문동 2624",
            title = "천제연폭포",
            recommendReason = "제주 허브동산은 가족 모두가 힐링할 수 있는 완벽한 장소입니다. 반짝이는 조명 아래에서 아름다운 허브 정원을 산책하며, 족욕체험도 가능해 온 가족이 함께 스트레스를 풀고 편안한 시간을 보낼 수 있습니다. 자연의 향기와 조명의 조화는 아이들에게도 특별한 경험이 될 것입니다.",
            coordinates = listOf(126.4125, 33.2459),
            serviceHours = listOf("09:00", "18:00"),
            reviews = TODO(),
            imageUrl = TODO()
        ),
        RecommendTourListDTO(
            id = ObjectId("64a8f1a2b7d2e3c111222335").toHexString(),
            categoryList = listOf(
                CategoryEnum.NIGHT_VIEW,
                CategoryEnum.ROMANTIC
            ),
            fullAddress = "제주특별자치도 제주시 조천읍 신북로 550",
            title = "에코랜드 전망대",
            recommendReason = "가족과 함께 힐링을 원하신다면, 새연교에서의 산책을 추천드립니다. 조명이 켜진 다리 위에서 바닷가의 야경을 감상하며 걷다 보면, 마음이 편안해지고 자연의 아름다움을 느낄 수 있습니다. 특히, 아이들과 함께 손을 잡고 걷는 동안 대화도 나누고, 소중한 순간을 나누기에 정말 좋은 곳이에요.",
            coordinates = listOf(126.6547, 33.4785),
            serviceHours = listOf("17:00", "21:00"),
            reviews = TODO(),
            imageUrl = TODO()
        )
    )

    @Deprecated(message = "전체 조회로 통합")
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

