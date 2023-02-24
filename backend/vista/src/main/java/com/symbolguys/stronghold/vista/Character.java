package com.symbolguys.stronghold.vista;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Character {
    private String name;
    private State state;
}
