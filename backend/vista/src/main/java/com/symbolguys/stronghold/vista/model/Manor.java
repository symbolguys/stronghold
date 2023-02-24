package com.symbolguys.stronghold.vista.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Manor {
    private List<Household> households;
}
