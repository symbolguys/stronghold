package com.symbolguys.stronghold.vista;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    private List<Character> characters;
    private String type;
    private Position position;
}
