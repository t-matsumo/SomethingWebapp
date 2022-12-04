package com.gmail.tatsukimatsumo.tempchat.infrastructure

import org.springframework.data.repository.CrudRepository

interface ChatMessageRepository: CrudRepository<ChatMessage, Long> {
//    fun findBySlug(slug: String): ChatMessage?
    fun findAllByOrderByCreatedAt(): Iterable<ChatMessage>
}