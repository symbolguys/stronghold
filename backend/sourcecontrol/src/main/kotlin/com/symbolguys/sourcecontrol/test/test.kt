package com.symbolguys.sourcecontrol.test

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
class TestController {

  @GetMapping("/")
  fun testGet(): Mono<String> {
    return Mono.just("Hello from SourceControl")
  }

}