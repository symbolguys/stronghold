package com.symbolguys.stronghold.vista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.symbolguys.stronghold.vista.model.Battle;
import com.symbolguys.stronghold.vista.model.Character;
import com.symbolguys.stronghold.vista.model.Configuration;
import com.symbolguys.stronghold.vista.model.Enemy;
import com.symbolguys.stronghold.vista.model.EnemyType;
import com.symbolguys.stronghold.vista.model.Member;
import com.symbolguys.stronghold.vista.model.Project;
import com.symbolguys.stronghold.vista.model.State;

import lombok.extern.java.Log;

@Log
@RestController
public class Controller {

    @Autowired
    ManorService manorService;

    @GetMapping("/configuration")
    public Configuration configuration() {

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
                .state(State.WINNING)
                .type(EnemyType.ORC)
                .build();

        Enemy enemy2 = Enemy.builder()
                .id("123")
                .name("Another Mr. Defect")
                .state(State.FIGHTING)
                .type(EnemyType.BEHOLDER)
                .build();

        Enemy enemy3 = Enemy.builder()
                .id("45699")
                .name("Orc")
                .state(State.WINNING)
                .type(EnemyType.ORC)
                .build();

        Battle battle = Battle.builder()
                .enemies(List.of(enemy))
                .character(manie)
                .position(Coordinates.wallI2)
                .build();

        Battle battle2 = Battle.builder()
                .enemies(List.of(enemy2, enemy2, enemy2))
                .character(manie)
                .position(Coordinates.wallI1)
                .build();

        Battle battleOutside = Battle.builder()
                .enemies(List.of(enemy3, enemy3))
                .character(rin)
                .position(Coordinates.outer)
                .build();

        Project projectHouseU1 = Project.builder()
                .characters(List.of(Character.builder()
                        .name("blacksmith")
                        .state(State.FIGHTING)
                        .build()))
                .type("blacksmith")
                .position(Coordinates.blacksmith)
                .build();

        Project projectHouse02 = Project.builder()
                .characters(List.of(Character.builder()
                        .name("outerSmallRedHouse")
                        .state(State.FIGHTING)
                        .build()))
                .type("outerSmallRedHouse")
                .position(Coordinates.outerSmallRedHouse)
                .build();

        Project projecthouseI1 = Project.builder()
                .characters(List.of(Character.builder()
                        .name("innerOrangeHouse")
                        .state(State.FIGHTING)
                        .build()))
                .type("innerOrangeHouse")
                .position(Coordinates.innerOrangeHouse)
                .build();

        Project projecthouseI2 = Project.builder()
                .characters(List.of(Character.builder()
                        .name("field")
                        .state(State.FIGHTING)
                        .build()))
                .type("field")
                .position(Coordinates.field)
                .build();

        List<Battle> battles = new ArrayList<Battle>();
        battles.add(battle);
        battles.add(battle2);
        battles.add(battleOutside);
        battles.addAll(characterBattles);

        List<Project> projects = List.of(
                projectHouseU1,
                projectHouse02,
                projecthouseI1,
                projecthouseI2);

        return Configuration.builder()
                .battles(battles)
                .projects(projects)
                .build();
    }

    private Battle createBattle(Character character) {
        Enemy enemy = Enemy.builder()
                .id("123")
                .name("Mr. Defect")
                .state(State.FIGHTING)
                .build();

        return Battle.builder()
                .enemies(List.of(enemy))
                .character(character)
                .position(Coordinates.wallI2)
                .build();
    }
}
