package com.goorm.maki.service

import com.goorm.maki.domain.document.NightTourDocument
import com.goorm.maki.domain.document.Tags
import com.goorm.maki.domain.dto.NightTourDTO
import com.goorm.maki.domain.dto.NightTourRequestDto
import com.goorm.maki.repository.NightTourRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class NightTourSpotService(
    private val nightTourRepository: NightTourRepository
) {

    fun createNightTour(req: NightTourRequestDto): NightTourDTO =
        with(req) {
            // Todo.chatGpt/api
            val coordinate = listOf(33.499623, 126.531188)
            // Todo. api ,,
            val lastBusInfo = listOf("213번 (22:00), 13번")

            val reqToDocument = NightTourDocument(
                title = title,
                fullAddress = fullAddress,
                coordinates = coordinate,
                categoryList = categoryList,
                serviceHours = serviceHours,
                description = description,
                imageId = imageId?.let { ObjectId(it) },
                lastBusInfo = lastBusInfo,
                tags = Tags( //Todo. gpt,,,? or 추후 insert,, api 통해서,,?
                    companionType = "혼자",
                    travelMethod = "도보",
                    placeMood = "차분",
                    activity = "산책"
                )
            )

            val result = nightTourRepository.save(reqToDocument)

            NightTourDTO.from(result)
        }
}