package com.rpodd.tinder_ai_backend.profiles;

public record Profile(
		String id,
		String firstname,
		String lastName,
		int age,
		String ethnicity,
		Gender gender,
		String bio,
		String imageUrl,
		String myersBriggsPersonalityType
		
		
		
) {
}
