package com.laixer.swabbr.domain.usecase

import com.laixer.swabbr.domain.model.Comment
import com.laixer.swabbr.domain.repository.CommentRepository
import io.reactivex.Single

class CommentsUseCase constructor(private val commentRepository: CommentRepository) {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        commentRepository.get(postId, refresh)
}
