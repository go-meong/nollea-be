package com.goorm.maki.config

import com.goorm.maki.constant.Const.PUBLIC_URL_PATTERNS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig {

    @Bean
    fun corsOriginPatterns(): CorsOriginPatterns = CorsOriginPatterns(listOf("*"))

    @Bean
    fun corsConfigurationSource(corsOriginPatterns: CorsOriginPatterns): UrlBasedCorsConfigurationSource {
        val configuration = CorsConfiguration()
            .apply {
                allowedOriginPatterns = corsOriginPatterns.patterns
                allowedMethods = listOf("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS")
                allowedHeaders = listOf("*")
                allowCredentials = true
            }

        return UrlBasedCorsConfigurationSource()
            .apply {
                this.registerCorsConfiguration("/**", configuration)
            }
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        corsConfigurationSource: UrlBasedCorsConfigurationSource,
    ): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource) } // ✅ 요거 추가!
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .logout { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    .requestMatchers(*PUBLIC_URL_PATTERNS).permitAll()
                    .anyRequest().permitAll()
            }
        return http.build()
    }
}