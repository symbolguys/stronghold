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

    @GetMapping("/test")
    fun getMockedPullRequest() : Flux<List<PullRequest>>{
        var prList=mutableListOf<PullRequest>()
        prList.add(PullRequest(-1,"MERGED","Great Title", Date(101199), Date(15122018), Date(12101990) ))
        return Flux.just(prList)
    }

    @GetMapping
    fun getAllPullRequests(): Flux<Iterable<PullRequest>> {
        return Flux.just(prRepo.findAll())
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
