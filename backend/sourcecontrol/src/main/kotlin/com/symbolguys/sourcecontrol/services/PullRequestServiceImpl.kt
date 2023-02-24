package com.symbolguys.sourcecontrol.services

import com.symbolguys.sourcecontrol.datamodel.PullRequest
import com.symbolguys.sourcecontrol.repository.PullRequestRepository
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Instant
import java.util.*

class PullRequestServiceImpl (private val prRepo: PullRequestRepository) : PullRequestService {


    override fun fetchAllPullRequestsIntoRepo(baseUrl : String, projectKey: String, repoSlug: String, bearerToken: String){
        var url= "https://$baseUrl/rest/api/latest/projects/$projectKey/repos/$repoSlug/pull-requests"
        var headers= HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.setBearerAuth(bearerToken)
        val request = HttpEntity<Any>(headers)

        val factory: ClientHttpRequestFactory = SimpleClientHttpRequestFactory()
        val restTemplate = RestTemplate(factory)

        val response = restTemplate.exchange(url, HttpMethod.GET, request, String::class.java)
        var pullRequests : JSONArray? = JSONArray()
        if (response.statusCodeValue == 200) {
            pullRequests = response.body?.let { JSONObject(it).getJSONArray("values") }
            // iterate over the pull requests and create instances of your data class
        } else {
            println("Failed to fetch pull requests: ${response.statusCodeValue} ${response.body}")
        }
        val pullRequestList = mutableListOf<PullRequest>()

        if (pullRequests != null) {
            for (i in 0 until pullRequests.length()) {
                val pr = pullRequests.getJSONObject(i)
                val id = pr.getInt("id").toLong()
                var state=pr.getString("state")
                val title = pr.getString("title")
                val destination=pr.getString("destination")
                var status=pr.getString("status")
                var quality=pr.getString("quality")
                var commits=pr.getLong("commits")
                var fileChanges=pr.getLong("fileChanges")
                var reviewers=pr.getLong("reviewers")
                var comments=pr.getLong("comments")
                val author = pr.getJSONObject("author").getString("display_name")
                val crDate = Date.from(Instant.ofEpochMilli(pr.getLong("createdDate")))
                var clDate= Date.from(Instant.ofEpochMilli(pr.getLong("closedDate")))
                var udDate=Date.from(Instant.ofEpochMilli(pr.getLong("updatedDate")))
                val pullRequest = PullRequest(id, title, destination, status, state, quality, udDate, clDate, crDate,commits, fileChanges, reviewers, comments, author )
                pullRequestList.add(pullRequest)
            }
        }

        prRepo.saveAll(pullRequestList)
    }


}