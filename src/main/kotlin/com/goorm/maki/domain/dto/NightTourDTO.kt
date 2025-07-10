package com.goorm.maki.domain.dto

import com.goorm.maki.constant.CategoryEnum
import com.goorm.maki.domain.document.NightTourDocument

data class NightTourDTO(
    val id: String?,
    val title: String,
    val fullAddress: String,
    val categoryList: List<CategoryEnum>,
    val serviceHours: List<String>,
    val description: String,
    val imageUrl: String? = null,
) {
    companion object {
        fun from(document: NightTourDocument, imgUrl: String?): NightTourDTO =
            with(document) {
                NightTourDTO(
                    id = id.toHexString(),
                    title = title,
                    fullAddress = fullAddress,
                    categoryList = categoryList,
                    serviceHours = serviceHours,
                    description = description,
                    imageUrl = imgUrl
                )
            }
    }
}

data class NightTourRequestDto(
    val fullAddress: String,
    val title: String,
    val serviceHours: List<String>,
    val categoryList: List<CategoryEnum>,
    val description: String,
    val imageId: String?,
)

data class NightTourPicDTO(
    val id: String,
    val originalName: String,  // 원본 파일명
    val contentType: String,   // MIME 타입 (image/jpeg 등)
    val size: Long,            // 파일 크기 (바이트)
    val base64: String         // base64로 인코딩된 데이터
)

data class NightTourRawDTO(
    val id: String,
    val title: String,
    val fullAddress: String,
    val coordinates: List<Double>,
    val categoryList: List<CategoryEnum>,
    val serviceHours: List<String>,
    val recommendReason: String?,
    val description: String,
    val imageId: String? = null,
    val reviews: List<String> = emptyList(),
    val positiveCount: Long = 0,
    val negativeCount: Long = 0,
) {
    companion object {
        fun from(document: NightTourDocument, recommendReason: String?): NightTourRawDTO = with(document) {
            NightTourRawDTO(
                id = id.toHexString(),
                title = title,
                fullAddress = fullAddress,
                coordinates = coordinates,
                categoryList = categoryList,
                serviceHours = serviceHours,
                description = description,
                imageId = imageId?.toHexString(),
                reviews = reviews ?: emptyList(),
                positiveCount = positiveCount ?: 0,
                negativeCount = negativeCount ?: 0,
                recommendReason = recommendReason ?: "추천사유없음"
            )
        }
    }
}

