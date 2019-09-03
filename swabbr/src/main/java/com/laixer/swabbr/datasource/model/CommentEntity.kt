package com.laixer.swabbr.datasource.model

import com.laixer.swabbr.domain.model.Comment
import com.squareup.moshi.Json

data class CommentEntity(
    @field:Json(name = "postId") val postId: String,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "body") val body: String
)

fun CommentEntity.mapToDomain(): Comment = Comment(postId, id, name, email, body)

fun List<CommentEntity>.mapToDomain(): List<Comment> = map { it.mapToDomain() }
