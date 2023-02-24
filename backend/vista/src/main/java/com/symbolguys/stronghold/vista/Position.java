package com.symbolguys.stronghold.vista;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {
    private int x;
    private int y;
    private int z;
}