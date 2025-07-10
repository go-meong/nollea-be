package com.goorm.maki.service

import com.goorm.maki.domain.dto.NightTourRawDTO
import com.goorm.maki.domain.dto.RecommendTourListDTO
import com.goorm.maki.domain.dto.RecommendTourRequestDto
import com.goorm.maki.domain.dto.ReviewRequestDTO
import com.goorm.maki.repository.NightTourImageRepository
import com.goorm.maki.repository.NightTourRepository
import org.bson.types.ObjectId
import org.springframework.ai.chat.client.ChatClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service

@Service
class RecommendTourService(
    private val nightTourRepository: NightTourRepository,
    private val nightTourImageRepository: NightTourImageRepository,
    private val chatClient: ChatClient
) {
    fun findRecommendTour(recommendTourRequestDto: RecommendTourRequestDto): List<RecommendTourListDTO> {
        // 야간 관광 명소 조화
        val nightTourList = nightTourRepository.findAll().map { NightTourRawDTO.from(
            it, null) }

        val message = """
            사용자 요청: $recommendTourRequestDto
            야간 관광지 목록: $nightTourList
            
            위 사용자 요청을 분석하여 야간 관광지 목록 중에서 가장 적합한 3개를 선택해주세요.
            각 추천 항목에 대해 NightTourDTO 형식으로 응답하되, recommendReason 필드에 추천 사유를 포함해주세요.
            
            추천 사유 작성 가이드라인:
            - 사용자의 요청사항과 해당 관광지의 특징을 구체적으로 연결지어 설명
            - 왜 이 관광지가 사용자에게 특별히 적합한지 상세히 기술
            - 관광지의 독특한 매력이나 경험할 수 있는 구체적인 활동 포함
            - 최소 2-3문장 이상의 상세한 설명으로 작성
            - 친근하고 상냥한 말투로 작성하여 사용자가 실제로 방문하고 싶도록 유도 
            
            응답 형식은 JSON 배열로 작성해주세요.
        """.trimIndent()

        // OpenAI LLM 호출
        val recommendedTourList = chatClient.prompt()
            .user(message)
            .call()
            .entity(object :
                ParameterizedTypeReference<List<NightTourRawDTO>>() {})
            ?: emptyList()

        val test: List<RecommendTourListDTO> = recommendedTourList.map { tour ->
            // 1. 이미지 도큐먼트 조회 (imageId → base64)
            val imageUrl = tour.imageId
                ?.let { nightTourImageRepository.findById(ObjectId(it)).orElse(null)?.base64 }

            // 2. 추천 사유가 이미 tour 안에 들어있다고 가정 (없으면 따로 넘겨야 함)
            val recommendReason = tour.recommendReason ?: "추천 사유 없음"

            // 3. 변환
            RecommendTourListDTO.from(
                rawDTO = tour,
                imageUrl = imageUrl,
                recommendReason = recommendReason
            )
        }

        // Review 퍼센트 계산
        return test
    }

    fun createReview(req: ReviewRequestDTO): Boolean {
        // 기존 NightTour 조회
        val nightTourDocument = nightTourRepository.findById(req.id) ?: return false

        // 중복되지 않은 리뷰만 추가
        val currentReviews = nightTourDocument.reviews ?: emptyList()
        val updatedReviews = if (req.review in currentReviews) {
            currentReviews
        } else {
            currentReviews + req.review
        }

        // liked에 따라서 positive, negative Count 증가
        val updatedPositiveCount = if (req.liked) {
            (nightTourDocument.positiveCount ?: 0) + 1
        } else {
            nightTourDocument.positiveCount ?: 0
        }
        val updatedNegativeCount = if (!req.liked) {
            (nightTourDocument.negativeCount ?: 0) + 1
        } else {
            nightTourDocument.negativeCount ?: 0
        }

        // NightTourDocument 업데이트
        val updatedDocument = nightTourDocument.copy(
            reviews = updatedReviews,
            positiveCount = updatedPositiveCount,
            negativeCount = updatedNegativeCount
        )
        nightTourRepository.save(updatedDocument)

        return true
    }
}