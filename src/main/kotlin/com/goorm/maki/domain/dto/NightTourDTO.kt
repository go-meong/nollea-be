package com.goorm.maki.domain.dto

import com.goorm.maki.constant.CategoryEnum
import org.bson.types.ObjectId

data class NightTourDTO(
    val id: ObjectId?,
    val fullAddress: String,
    val zipcode: String,
    val categoryList: List<CategoryEnum>,
    val serviceHours: List<String>,
    val description: String,
    val imageId: ObjectId?,
)

data class NightTourRequestDto(
    val fullAddress: String,
    val zipcode: String,
    val serviceHours: List<String>,
    val categoryList: List<CategoryEnum>,
    val description: String,
    val imageId: ObjectId?,
)

data class NightTourPicDTO(
    val originalName: String,  // 원본 파일명
    val savedName: String,     // 서버에 저장된 파일명 (UUID 등)
    val path: String,          // 저장 경로 (예: /upload/...)
    val size: Long,            // 파일 크기 (바이트)
    val contentType: String    // MIME 타입 (image/jpeg 등)
)

data class NightTourPicRequestDto(
    val description: String,
)