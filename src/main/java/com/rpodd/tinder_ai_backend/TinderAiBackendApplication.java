package com.rpodd.tinder_ai_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rpodd.tinder_ai_backend.profiles.Gender;
import com.rpodd.tinder_ai_backend.profiles.Profile;
import com.rpodd.tinder_ai_backend.profiles.ProfileRepository;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner{

	@Autowired
	private ProfileRepository  profileRepository;
	
	
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
	
	}
}
