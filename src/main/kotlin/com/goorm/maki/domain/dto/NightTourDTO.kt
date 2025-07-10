package com.goorm.maki.domain.dto

import com.goorm.maki.constant.CategoryEnum
import com.goorm.maki.domain.document.NightTourDocument
import org.bson.types.ObjectId

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
        fun from(document: NightTourDocument): NightTourDTO =
            with(document) {
                NightTourDTO(
                    id = id.toHexString(),
                    title = title,
                    fullAddress = fullAddress,
                    categoryList = categoryList,
                    serviceHours = serviceHours,
                    description = description,
                    imageUrl = imageId?.toHexString()
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
    val id: ObjectId,
    val originalName: String,  // 원본 파일명
    val savedName: String,     // 서버에 저장된 파일명 (UUID 등)
    val path: String,          // 저장 경로 (예: /upload/...)
    val size: Long,            // 파일 크기 (바이트)
    val contentType: String    // MIME 타입 (image/jpeg 등)
)
