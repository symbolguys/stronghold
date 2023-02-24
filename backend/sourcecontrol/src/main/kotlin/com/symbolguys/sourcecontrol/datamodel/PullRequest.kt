package com.symbolguys.sourcecontrol.datamodel

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "\"pull_request\"")
data class PullRequest(
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long?,
  val state: String?,
  val title: String?,
  val updateDate: Date?,
  val closeDate: Date?,
  val createDate: Date?,
  val author: String?
) {
  constructor(): this(null, null, null, null, null, null,null)
}