package com.goorm.maki.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Nollea API 문서")
                    .description("API 명세서입니다")
                    .version("v1.0.0")
            )
            .servers(
                listOf(
                    Server().url("https://nollea-backend.goorm.training")
                )
            )
    }
}