package com.rpodd.tinder_ai_backend.conversations;
import java.util.*;
public record Conversation(
		String id,
		String profileId,
		List<ChatMessage> messages) {

}
