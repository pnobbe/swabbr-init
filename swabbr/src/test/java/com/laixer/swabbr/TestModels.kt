package com.laixer.swabbr

import com.laixer.swabbr.datasource.model.CommentEntity
import com.laixer.swabbr.datasource.model.PostEntity
import com.laixer.swabbr.datasource.model.UserEntity
import com.laixer.swabbr.domain.model.Comment
import com.laixer.swabbr.domain.model.Post
import com.laixer.swabbr.domain.model.User
import com.laixer.swabbr.domain.usecase.CombinedUserPost

val user = User("userId", "name", "username", "email")
val post = Post("userId", "postId", "title", "body")
val comment = Comment("postId", "commentId", "name", "email", "body")

val combinedUserPost = CombinedUserPost(user, post)

val userEntity = UserEntity("userId", "name", "username", "email")
val postEntity = PostEntity("userId", "postId", "title", "body")
val commentEntity = CommentEntity("postId", "commentId", "name", "email", "body")
