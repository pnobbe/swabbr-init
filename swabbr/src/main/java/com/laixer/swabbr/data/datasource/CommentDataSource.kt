package com.laixer.swabbr.data.datasource

import com.laixer.swabbr.domain.model.Comment
import io.reactivex.Single

interface CommentCacheDataSource {

    fun get(postId: String): Single<List<Comment>>

    fun set(postId: String, list: List<Comment>): Single<List<Comment>>
}

interface CommentRemoteDataSource {

    fun get(postId: String): Single<List<Comment>>
}
