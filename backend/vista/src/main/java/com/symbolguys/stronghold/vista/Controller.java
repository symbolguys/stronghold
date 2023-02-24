package com.symbolguys.stronghold.vista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.symbolguys.stronghold.vista.model.Battle;
import com.symbolguys.stronghold.vista.model.Character;
import com.symbolguys.stronghold.vista.model.Configuration;
import com.symbolguys.stronghold.vista.model.Enemy;
import com.symbolguys.stronghold.vista.model.Member;
import com.symbolguys.stronghold.vista.model.Position;
import com.symbolguys.stronghold.vista.model.Project;
import com.symbolguys.stronghold.vista.model.State;

import io.netty.util.internal.ThreadLocalRandom;
import lombok.extern.java.Log;

@Log
@RestController
public class Controller {

    @GetMapping("/configuration")
    public Configuration configuration() {

        /*
         * String uri = "http://localhost:3001/get-manors";
         * RestTemplate restTemplate = new RestTemplate();
         * Manor[] manors = restTemplate.getForObject(uri, Manor[].class);
         */

        Member[] members = new RestTemplate().getForObject("http://manor:8080/get-members",
                Member[].class);

        List<Character> characters = Arrays.asList(members).stream().map(member -> {
            return Character.builder()
                    .name(member.getName())
                    .state(State.FIGHTING)
                    .build();
        }).collect(Collectors.toList());

        List<Battle> characterBattles = characters.stream().map(character -> {
            return createBattle(character);
        }).collect(Collectors.toList());

        Character rin = Character.builder()
                .name("RIN")
                .state(State.FIGHTING)
                .build();

        Character manie = Character.builder()
                .name("MANIE")
                .state(State.DEFEND)
                .build();

        Enemy enemy = Enemy.builder()
                .id("456")
                .name("Mr. Defect")
                .state("WINNING")
                .build();

        Enemy enemy2 = Enemy.builder()
            .id("123")
            .name("Another Mr. Defect")
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

        Battle battle2 = Battle.builder()
            .enemies(List.of(enemy2, enemy2))
            .character(manie)
            .position(Position.builder()
                .x(7)
                .y(8)
                .z(9)
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

        List<Battle> battles = new ArrayList<Battle>();
        battles.add(battle);
        battles.add(battle2);
        battles.addAll(characterBattles);

        log.info(battles.toString());

        return Configuration.builder()
                .battles(battles)
                .projects(List.of(project))
                .build();
    }

    private Battle createBattle(Character character) {
        Enemy enemy = Enemy.builder()
                .id("123")
                .name("Mr. Defect")
                .state("FIGHTING")
                .build();

        return Battle.builder()
                .enemies(List.of(enemy))
                .character(character)
                .position(Position.builder()
                        .x(randomPositionInt())
                        .y(randomPositionInt())
                        .z(randomPositionInt())
                        .build())
                .build();
    }

    private int randomPositionInt() {
        return ThreadLocalRandom.current().nextInt(0, 10 + 1);
    }
}
