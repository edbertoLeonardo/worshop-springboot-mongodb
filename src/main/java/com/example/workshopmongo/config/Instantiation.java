package com.example.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AuthorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;


	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
		simpleDate.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, simpleDate.parse("21/03/2108"), "Partiu viajem", "Vou viajar para SP", new AuthorDTO(maria));
		Post post2 = new Post(null, simpleDate.parse("23/03/2108"), "Bom dia!", "Acordei feliz hoje", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", simpleDate.parse("21/01/2020"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", simpleDate.parse("22/01/2020"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("TEnha um ótimo dia!", simpleDate.parse("22/01/2020"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}
