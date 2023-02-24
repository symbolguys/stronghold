package com.symbolguys.ci

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CiApplication

fun main(args: Array<String>) {
	runApplication<CiApplication>(*args)
}
