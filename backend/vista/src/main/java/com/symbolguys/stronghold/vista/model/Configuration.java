package com.symbolguys.stronghold.vista.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Configuration {
    private List<Battle> battles;
    private List<Project> projects;
}
