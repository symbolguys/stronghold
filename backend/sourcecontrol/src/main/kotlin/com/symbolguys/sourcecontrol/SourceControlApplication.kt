package com.symbolguys.sourcecontrol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SourceControlApplication

fun main(args: Array<String>) {
	runApplication<SourceControlApplication>(*args)
}
