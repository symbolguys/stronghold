package com.symbolguys.stronghold.vista;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Enemy {
    private String id;
    private String name;
    private String state;
}
