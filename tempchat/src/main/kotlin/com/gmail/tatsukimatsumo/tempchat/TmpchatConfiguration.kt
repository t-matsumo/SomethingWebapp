package com.gmail.tatsukimatsumo.tempchat

import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessage
import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessageRepository
import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessageSender
import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessageSenderRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TmpchatConfiguration {
    @Bean
    fun databaseInitializer(
        chatMessageRepository: ChatMessageRepository,
        chatMessageSenderRepository: ChatMessageSenderRepository
    ) = ApplicationRunner {
        val sender = chatMessageSenderRepository.save(ChatMessageSender("senderName 1"))
        chatMessageRepository.save(ChatMessage("chat message1", sender))
        chatMessageRepository.save(ChatMessage("chat message2", sender))
        chatMessageRepository.save(ChatMessage("chat message3", sender))
    }
}