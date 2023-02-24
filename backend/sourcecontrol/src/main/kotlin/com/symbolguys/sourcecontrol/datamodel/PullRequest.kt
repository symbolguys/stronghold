package com.symbolguys.sourcecontrol.datamodel

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "\"pull_request\"")
data class PullRequest(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long?,
  val title: String?,
  val destination: String?,
  val status: String?,
  val state: String?,
  val updateDate: Date?,
  val closeDate: Date?,
  val createDate: Date?,
  val fileChanges: Long?,
  val reviewers: Long?,
  val comments: Long?
) {
  constructor(): this(null, null, null, null, null, null, null, null, null, null, null)
}