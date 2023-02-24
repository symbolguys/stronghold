package com.symbolguys.stronghold.vista.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Battle {
    private List<Enemy> enemies;
    private Character character;
    private Position position;
}
