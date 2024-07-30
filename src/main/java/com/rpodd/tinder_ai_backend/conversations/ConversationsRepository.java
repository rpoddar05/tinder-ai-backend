package com.rpodd.tinder_ai_backend.conversations;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationsRepository extends MongoRepository<Conversation, String> {

}
