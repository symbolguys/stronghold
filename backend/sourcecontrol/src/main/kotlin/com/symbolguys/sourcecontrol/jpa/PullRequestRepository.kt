package com.symbolguys.sourcecontrol.jpa

import PullRequest
import org.springframework.data.r2dbc.repository.R2dbcRepository
import java.util.*


interface PullRequestRepository : R2dbcRepository<PullRequest, UUID>
