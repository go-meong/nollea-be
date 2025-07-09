package com.goorm.maki.controller

import com.goorm.maki.annotation.RowBody
import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SpringAiController(
    private val chatClient: ChatClient
) {

    @RowBody
    @GetMapping("/chat")
    fun callTest(message: String) = chatClient.prompt()
        .user(message)
        .call()
        .content();


}