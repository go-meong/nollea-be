package com.goorm.maki.controller

import com.goorm.maki.annotation.RowBody
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SpringAiController(
    private val chatClient: ChatClient
) {

    @RowBody @Hidden
    @GetMapping("/api/v1/chat")
    fun callTest(message: String) = chatClient.prompt()
        .user(message)
        .call()
        .content();


}