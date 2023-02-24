package com.symbolguys.stronghold.vista;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

	@GetMapping("/teams")
	public List<Team> teams() {

		Position position = Position.builder().x(1).y(2).z(3).build();
		Character character = Character.builder()
		.name("RIN")
		.position(position)
		.build();

		Team team = Team.builder().name("Symbology").characters(List.of(character)).build();

		return List.of(team);
	}
}
