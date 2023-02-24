package com.symbolguys.sourcecontrol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories("com.symbolguys.sourcecontrol.*")
@ComponentScan(basePackages = ["com.symbolguys.sourcecontrol.*"])
@EntityScan("com.symbolguys.sourcecontrol.*")
class SourceControlApplication

fun main(args: Array<String>) {
	runApplication<SourceControlApplication>(*args)
}
