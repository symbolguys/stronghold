package com.symbolguys.stronghold.vista.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {
    private Float x;
    private Float y;
    private Float z;
}
