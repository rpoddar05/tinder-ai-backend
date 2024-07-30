package com.rpodd.tinder_ai_backend.conversations;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		//verifying if id exist to continue conversation
		profileRepository.findById(request.profileId())
			.orElseThrow(()-> new ResponseStatusException (HttpStatus.NOT_FOUND));
		
		Conversation conversation = new Conversation(
				UUID.randomUUID().toString(),
				request.profileId(),
				new ArrayList<>());

		conversationsRepository.save(conversation);
		
		return conversation;

	}

	public record CreateConversationRequest(String profileId) {
	}

}
