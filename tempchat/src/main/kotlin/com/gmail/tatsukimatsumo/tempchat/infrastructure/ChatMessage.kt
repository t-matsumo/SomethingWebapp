package com.gmail.tatsukimatsumo.tempchat.infrastructure

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class ChatMessage(
    var content: String,
    @ManyToOne var sender: ChatMessageSender,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null
)