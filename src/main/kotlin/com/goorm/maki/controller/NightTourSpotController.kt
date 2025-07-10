package com.goorm.maki.controller

import com.goorm.maki.constant.CategoryEnum
import com.goorm.maki.domain.dto.NightTourDTO
import com.goorm.maki.domain.dto.NightTourRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*

@Tag(name = "야간관광명소", description = "야간 관광명소 API")
@RestController
@RequestMapping("/api/v1/night-tour")
class NightTourSpotController {

    @GetMapping
    @Operation(summary = "야간 관광명소 목록 조회", description = "야간 관광명소를 조회하는 API입니다.")
    fun findNightTour(): List<NightTourDTO> {
        return listOf(
            NightTourDTO(
                id = ObjectId("64a8f0f8b7d2e3c123456789"),
                fullAddress = "서울특별시 중구 세종대로 110",
                zipcode = "04524",
                categoryList = listOf(CategoryEnum.FOOD, CategoryEnum.NIGHT_MARKET),
                serviceHours = listOf("18:00-23:00", "12:00-21:00"),
                description = "서울에서 가장 유명한 야시장 중 하나입니다.",
                imageId = ObjectId("64a8f1a2b7d2e3c987654321")
            ),
            NightTourDTO(
                id = ObjectId("64a8f123b7d2e3c112233445"),
                fullAddress = "부산광역시 해운대구 달맞이길 25",
                zipcode = "48095",
                categoryList = listOf(CategoryEnum.NATURE, CategoryEnum.NIGHT_VIEW, CategoryEnum.ROMANTIC),
                serviceHours = listOf("09:00-22:00"),
                description = "바다와 야경이 어우러진 로맨틱한 산책길입니다.",
                imageId = ObjectId("64a8f1a2b7d2e3c111222333")
            )
        )
    }

    @PostMapping
    @Operation(summary = "야간 관광명소 등록", description = "야간 관광명소를 등록하는 API입니다.")
    fun createNightTour(@RequestBody nightTourRequestDto: NightTourRequestDto): NightTourDTO {
        return NightTourDTO(
            id = ObjectId("64a8f123b7d2e3c112233445"),
            fullAddress = "부산광역시 해운대구 달맞이길 25",
            zipcode = "48095",
            categoryList = listOf(CategoryEnum.NATURE, CategoryEnum.NIGHT_VIEW, CategoryEnum.ROMANTIC),
            serviceHours = listOf("09:00-22:00"),
            description = "바다와 야경이 어우러진 로맨틱한 산책길입니다.",
            imageId = ObjectId("64a8f1a2b7d2e3c111222333")
        )
    }

    @GetMapping("/{placeId}")
    @Operation(summary = "야간 관광명소 상세 조회", description = "야간 관광명소를 상세조회하는 API입니다.")
    fun getNightTour(@PathVariable placeId: String): NightTourDTO = NightTourDTO(id = ObjectId("64a8f123b7d2e3c112233445"),
        fullAddress = "부산광역시 해운대구 달맞이길 25",
        zipcode = "48095",
        categoryList = listOf(CategoryEnum.NATURE, CategoryEnum.NIGHT_VIEW, CategoryEnum.ROMANTIC),
        serviceHours = listOf("09:00-22:00"),
        description = "바다와 야경이 어우러진 로맨틱한 산책길입니다.",
        imageId = ObjectId("64a8f1a2b7d2e3c111222333"))

}