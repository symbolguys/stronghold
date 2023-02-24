package com.symbolguys.stronghold.vista;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

	@GetMapping("/characters")
	public List<Character> characters() {
		List<Character> list = new ArrayList<>();
		Position position = Position.builder().x(1).y(2).z(3).build();
		Character character = Character.builder()
		.name("RIN")
		.position(position)
		.build();
		list.add(character);
		return list;
	}
}
