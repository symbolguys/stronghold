package com.symbolguys.sourcecontrol.repository

import com.symbolguys.sourcecontrol.datamodel.PullRequest
import org.springframework.data.repository.CrudRepository

interface PullRequestRepository : CrudRepository<PullRequest, Long>