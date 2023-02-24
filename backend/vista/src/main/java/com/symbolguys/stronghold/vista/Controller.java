package com.symbolguys.stronghold.vista;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/configuration")
	public Configuration configuration() {

        Character rin = Character.builder()
		.name("RIN")
		.state(State.FIGHTING)
		.build();

		Character manie = Character.builder()
		.name("MANIE")
		.state(State.DEFEND)
		.build();

        Enemy enemy = Enemy.builder()
        .id("123")
        .name("Mr. Defect")
        .state("FIGHTING")
        .build();

        Battle battle = Battle.builder()
        .enemies(List.of(enemy))
        .character(manie)
        .position(Position.builder()
        .x(4)
        .y(5)
        .z(6)
        .build())
        .build();

        Project project = Project.builder()
        .characters(List.of(rin))
        .type("House")
        .position(Position.builder()
            .x(1)
            .y(2)
            .z(3)
            .build())
        .build();


        return Configuration.builder()
        .battles(List.of(battle))
        .projects(List.of(project))
        .build();
	}
}
