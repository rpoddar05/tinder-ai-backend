package com.rpodd.tinder_ai_backend.conversations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rpodd.tinder_ai_backend.profiles.ProfileRepository;

@RestController
public class ConversationController {

	@Autowired
	private ConversationsRepository conversationsRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@PostMapping("/conversations")
	public Conversation createNewConversation(@RequestBody CreateConversationRequest request) {
		// verifying if id exist to continue conversation
		profileRepository.findById(request.profileId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Unable to find the profile with ID " + request.profileId()));

		Conversation conversation = new Conversation(UUID.randomUUID().toString(), request.profileId(),
				new ArrayList<>());

		conversationsRepository.save(conversation);

		return conversation;

	}

	@PostMapping("/conversations/{conversationId}")
	public Conversation addMessageToConversation(@PathVariable String conversationId,
			@RequestBody ChatMessage chatMessage) {
		// here we verify thst the conversation exists
		Conversation conversation = conversationsRepository.findById(conversationId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation Id not found " + conversationId));

		// here we verify that the author of that message exists
		profileRepository.findById(chatMessage.authorId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Unable to find the profile with ID " + chatMessage.authorId()));

		// TODO: need to validate that the author of a msg happens to be only the
		// profile associated with the
		// msg user

		// we tracking our own time
		ChatMessage messageWithTime = new ChatMessage(chatMessage.messageText(), chatMessage.authorId(),
				LocalDateTime.now());

		conversation.messages().add(messageWithTime);

		conversationsRepository.save(conversation);

		return conversation;

	}

	public record CreateConversationRequest(String profileId) {
	}

}
