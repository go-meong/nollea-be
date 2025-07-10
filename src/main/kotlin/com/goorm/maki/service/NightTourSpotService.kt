package com.goorm.maki.service

import com.goorm.maki.client.KakaoLocalClient
import com.goorm.maki.domain.document.NightTourDocument
import com.goorm.maki.domain.document.NightTourImageDocument
import com.goorm.maki.domain.dto.NightTourDTO
import com.goorm.maki.domain.dto.NightTourPicDTO
import com.goorm.maki.domain.dto.NightTourRequestDto
import com.goorm.maki.repository.NightTourImageRepository
import com.goorm.maki.repository.NightTourRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class NightTourSpotService(
    private val nightTourRepository: NightTourRepository,
    private val nightTourImageRepository: NightTourImageRepository,
    private val kakaoLocalClient: KakaoLocalClient,
) {

    fun createNightTour(req: NightTourRequestDto): NightTourDTO =
        with(req) {
            // 좌표 계산
            val coordinate = kakaoLocalClient.searchAddress(fullAddress) ?: listOf(33.499623, 126.531188)

            val reqToDocument = NightTourDocument(
                title = title,
                fullAddress = fullAddress,
                coordinates = coordinate,
                categoryList = categoryList,
                serviceHours = serviceHours,
                description = description,
                imageId = imageId?.let { ObjectId(it) }
            )

            val result = nightTourRepository.save(reqToDocument)

            NightTourDTO.from(result, null)
        }

    fun createNightTourPic(file: MultipartFile): NightTourPicDTO {
        val base64Data = encodeToBase64(file)

        val doc = NightTourImageDocument(
            originalName = file.originalFilename ?: "unknown.jpg",
            contentType = file.contentType ?: "image/jpeg",
            size = file.size,
            base64 = base64Data
        )
        
        val result = nightTourRepository.saveImage(doc)

        return NightTourPicDTO(
            id = doc.id.toHexString(),
            originalName = file.originalFilename ?: "unknown",
            size = file.size,
            contentType = file.contentType ?: "application/octet-stream",
            base64 = result.base64,
        )
    }


    private fun encodeToBase64(file: MultipartFile): String {
        val fileBytes = file.bytes
        val base64 = Base64.getEncoder().encodeToString(fileBytes)
        val contentType = file.contentType ?: "application/octet-stream"
        return "data:$contentType;base64,$base64"
    }

    fun findNightTour(): List<NightTourDTO> {
        val tours = nightTourRepository.findAll()
        val imageIdList = tours.mapNotNull { it.imageId }.toSet()
        val images = nightTourImageRepository.findAllById(imageIdList).associateBy { it.id }

        return tours.map { tour ->
            val imageUrl = tour.imageId?.let { images[it]?.base64 }
            NightTourDTO.from(tour, imageUrl)
        }
    }

}