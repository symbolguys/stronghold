package com.symbolguys.sourcecontrol.services

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
interface PullRequestService {
    fun fetchAllPullRequestsIntoRepo(baseUrl : String, projectKey: String, repoSlug: String,bearerToken: String)
}