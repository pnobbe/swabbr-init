package com.laixer.swabbr.domain.repository

import com.laixer.swabbr.domain.model.Comment
import io.reactivex.Single

interface CommentRepository {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>>
}
