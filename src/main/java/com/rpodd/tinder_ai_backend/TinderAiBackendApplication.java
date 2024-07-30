package com.rpodd.tinder_ai_backend;

import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rpodd.tinder_ai_backend.conversations.ChatMessage;
import com.rpodd.tinder_ai_backend.conversations.Conversation;
import com.rpodd.tinder_ai_backend.conversations.ConversationsRepository;
import com.rpodd.tinder_ai_backend.profiles.Gender;
import com.rpodd.tinder_ai_backend.profiles.Profile;
import com.rpodd.tinder_ai_backend.profiles.ProfileRepository;

import java.util.*;
import java.time.LocalDateTime;


@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner{

	@Autowired
	private ProfileRepository  profileRepository;
	
	@Autowired
	private ConversationsRepository conversationsRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}
	//this tells compiler to run after loading main class
	public void run(String... args) {

	Profile profile = new Profile(
			"1",
			"Rahul",
			"Poddar",
			32,
			"Indian",
			Gender.MALE,
			"Software Engineer",
			"foo.jpg",
			"INTP");
	
	profileRepository.save(profile);
	profileRepository.findAll().forEach(System.out::println);
	
	
	Conversation conversation = new Conversation(
			"1",
			profile.id(), 
			List.of(new ChatMessage("Hello", profile.id(), LocalDateTime.now())));
	
	conversationsRepository.save(conversation);
	conversationsRepository.findAll().forEach(System.out::print);;
	
	
	}
}
