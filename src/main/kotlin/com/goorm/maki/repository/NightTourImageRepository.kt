package com.goorm.maki.repository

import com.goorm.maki.domain.document.NightTourImageDocument
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NightTourImageRepository : MongoRepository<NightTourImageDocument, ObjectId> {

}