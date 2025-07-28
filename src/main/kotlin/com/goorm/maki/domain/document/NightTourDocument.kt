package com.goorm.maki.domain.document

import com.goorm.maki.constant.CategoryEnum
import com.goorm.maki.constant.CollectionNames
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = CollectionNames.NIGHT_TOUR)
data class NightTourDocument(
    @Id
    val id: ObjectId = ObjectId(),
    val title: String,
    val fullAddress: String,
    val coordinates: List<Double>,
    val categoryList: List<CategoryEnum>,
    val serviceHours: List<String>,
    val description: String,
    val imageId: ObjectId?,
    val reviews: List<String>? = emptyList(),
    val positiveCount: Long? = 0,
    val negativeCount: Long? = 0,
    )

