package com.symbolguys.stronghold.vista;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Team {
    private String name;
    private List<Character> characters;
}
