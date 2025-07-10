package com.goorm.maki.client

import com.goorm.maki.domain.dto.KakaoAddressResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod

@Component
class KakaoLocalClient(
    @Value("\${kakao.developers.app.api-key}") private val apiKey: String,
    private val restTemplate: RestTemplate
) {

    fun searchAddress(query: String): List<Double>? {
        val url = "https://dapi.kakao.com/v2/local/search/address.json"

        val headers = HttpHeaders().apply {
            set("Authorization", "KakaoAK $apiKey")
        }

        val entity = HttpEntity<String>(headers)

        return try {
            val response = restTemplate.exchange(
                "$url?query=$query",
                HttpMethod.GET,
                entity,
                KakaoAddressResponse::class.java
            )
            response.body?.documents?.firstOrNull()?.let { document ->
                listOf(document.y.toDouble(), document.x.toDouble())
            }
        } catch (e: Exception) {
            null
        }
    }
}