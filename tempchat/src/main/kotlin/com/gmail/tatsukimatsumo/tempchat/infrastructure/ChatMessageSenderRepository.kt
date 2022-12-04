package com.gmail.tatsukimatsumo.tempchat.infrastructure

import org.springframework.data.repository.CrudRepository

interface ChatMessageSenderRepository: CrudRepository<ChatMessageSender, Long> {
}