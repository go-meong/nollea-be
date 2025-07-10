package com.goorm.maki.constant

enum class CategoryEnum(val description: String) {
    FOOD("음식점"),
    NIGHT_MARKET("야시장"),
    NATURE("자연"),
    FESTIVAL("축제"),
    WALKING_PATH("산책길"),
    NIGHT_VIEW("야경"),
    ROMANTIC("로맨틱");

    companion object {
        fun fromKoreanName(name: String): CategoryEnum? {
            return values().find { it.description == name }
        }
    }
}