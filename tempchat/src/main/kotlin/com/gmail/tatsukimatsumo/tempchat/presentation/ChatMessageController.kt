package com.gmail.tatsukimatsumo.tempchat.presentation

import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessage
import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessageRepository
import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessageSender
import com.gmail.tatsukimatsumo.tempchat.infrastructure.ChatMessageSenderRepository
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/chatmessage")
@CrossOrigin(origins = ["http://localhost:3000"])
class ChatMessageController(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatMessageSenderRepository: ChatMessageSenderRepository
) {
    @GetMapping("/")
    fun findAll() = chatMessageRepository.findAllByOrderByCreatedAt()

    @PostMapping("/")
    fun crateChatMessage(@RequestBody chatMessage: ChatMessage): ChatMessage {
        val sender = chatMessageSenderRepository.save(ChatMessageSender(chatMessage.sender.name))
        return chatMessageRepository.save(ChatMessage(chatMessage.content, sender))
    }

//    @GetMapping("/{slug}")
//    fun findOne(@PathVariable slug: String) =
//        repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist"
}
