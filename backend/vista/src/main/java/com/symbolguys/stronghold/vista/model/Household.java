package com.symbolguys.stronghold.vista.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Household {
    private String name;
    private String section;
    private List<Member> members;
}
