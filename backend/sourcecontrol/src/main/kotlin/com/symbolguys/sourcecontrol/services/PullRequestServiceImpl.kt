package com.symbolguys.sourcecontrol.services

import PullRequest
import com.symbolguys.sourcecontrol.jpa.PullRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
class PullRequestServiceImpl (private val prRepo: PullRequestRepository) : PullRequestService {


    override fun fetchAllPullRequestsIntoRepo(baseUrl : String, projectKey: String, repoSlug: String){
        var url= "https://$baseUrl/rest/api/latest/projects/$projectKey/repos/$repoSlug/pull-requests"
        var httpHeaders= HttpHeaders().set("Accept","application/json")
        val restTemplate = RestTemplate()
        val requestEntity = HttpEntity<Any>(httpHeaders)
        val responseType = object : ParameterizedTypeReference<List<PullRequest>>() {}
        val responseEntity: ResponseEntity<List<PullRequest>> = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType)
        val pullRequests: List<PullRequest> = responseEntity.body ?: emptyList()
        prRepo.saveAll(pullRequests)
    }


}