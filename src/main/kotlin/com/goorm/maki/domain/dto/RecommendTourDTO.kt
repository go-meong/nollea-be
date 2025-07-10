package com.goorm.maki.domain.dto

import com.goorm.maki.constant.CategoryEnum
import org.bson.types.ObjectId

data class RecommendTourDTO(
    val id: ObjectId?,
    val title: String,
    val fullAddress: String,
    val coordinates: List<Double>,
    val zipcode: String,
    val categoryList: List<CategoryEnum>,
    val serviceHours: List<String>,
    val description: String,
    val imageId: ObjectId?,
    val congestionLevel: String?,
    val lastBusInfo: String?,
    val reviews: List<String> = emptyList(),
    val positiveCount: Long,
    val negativeCount: Long,
)
data class RecommendTourRequestDto(
    val companionType: String,  // 같이 여행 온 사람 유형
    val travelMethod: String,           // 여행 방식 (대중교통, 렌터카 등)
    val placeMood: String,      // 원하는 장소 분위기
    val activity: String       // 어떤 활동 (산책, 맛집, 공연 등)
)
data class RecommendTourListDTO(
    val id: String,
    val categoryList: List<CategoryEnum>,
    val fullAddress: String,
    val title: String,
    val coordinates: List<Double>,
    val serviceHours: List<String>,
)

data class RecommendTourResponseDTO(
    val id: String,
    val title: String,
    val fullAddress: String,
    val serviceHours: List<String>,
    val description: String,
    val congestionLevel: String?,
    val lastBusInfo: String?,
    val positiveRate : Int,
    val negativeRate : Long,
    val reviews: List<String> = emptyList(),
    val imageUrl: String?,
)

data class ReviewRequestDTO(
    val id: String,
    val review: String,
    val liked: Boolean,
)