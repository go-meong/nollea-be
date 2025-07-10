package com.goorm.maki.domain.document

import com.goorm.maki.constant.CollectionNames
import jakarta.persistence.Id
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = CollectionNames.NIGHT_TOUR_IMAGE)
data class NightTourImageDocument(
    @Id
    val id: ObjectId = ObjectId(),
    val originalName: String,  // 원본 파일명
    val contentType: String,   // MIME 타입 (image/jpeg 등)
    val size: Long,            // 파일 크기 (바이트)
    val base64: String         // base64로 인코딩된 데이터
)
