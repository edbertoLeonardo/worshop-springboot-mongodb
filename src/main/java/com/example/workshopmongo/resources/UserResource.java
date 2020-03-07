package com.example.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	//Tambem serve a anotação @GetMapping
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		
		User user = new User("1", "Fulano de Tal", "fulano@gmail");
		User user2 = new User("2", "Beltrno de Tal", "beltrano@gmail");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(user, user2));
		return ResponseEntity.ok().body(list);
	}
	
}
