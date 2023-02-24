package com.symbolguys.stronghold.vista;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Household {
    private String name;
    private List<Member> members;
}
