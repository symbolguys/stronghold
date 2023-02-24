package com.symbolguys.sourcecontrol.controllers

import com.symbolguys.sourcecontrol.datamodel.PullRequest
import com.symbolguys.sourcecontrol.repository.PullRequestRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono;
import java.util.*

@RestController
@RequestMapping("/pullRequests")
class PullRequestController (val prRepo: PullRequestRepository) {

    @GetMapping
    fun getAllPullRequests(): Iterable<PullRequest> {
        return prRepo.findAll()
    }

    @PostMapping
    fun createPullRequest(@RequestBody pullRequest: PullRequest): Mono<PullRequest> {
        return Mono.just(prRepo.save(pullRequest))
    }

    @DeleteMapping("/{id}")
    fun deletePullRequest(@PathVariable id: Long): Mono<Unit> {
        return Mono.just(prRepo.deleteById(id))
    }

    @PutMapping
    fun updatePullRequest(@RequestBody pullRequest: PullRequest): Mono<PullRequest>{
        return Mono.just(prRepo.save(pullRequest))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<PullRequest>{
        return Mono.just(prRepo.findById(id).orElse(null))
    }
}
