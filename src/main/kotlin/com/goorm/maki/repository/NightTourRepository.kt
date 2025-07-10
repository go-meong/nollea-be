package com.goorm.maki.repository

import com.goorm.maki.domain.document.NightTourDocument
import com.goorm.maki.domain.document.NightTourImageDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class NightTourRepository(
    private val mongoTemplate: MongoTemplate
) {
    fun save(nightTourDocument: NightTourDocument): NightTourDocument =
        mongoTemplate.save(nightTourDocument)

    fun saveImage(nightTourImageDocument: NightTourImageDocument): NightTourImageDocument =
        mongoTemplate.save(nightTourImageDocument)

    fun findAll(): List<NightTourDocument> = mongoTemplate.findAll(NightTourDocument::class.java)

    fun findById(id: String): NightTourDocument? = mongoTemplate.findById(id, NightTourDocument::class.java)
}