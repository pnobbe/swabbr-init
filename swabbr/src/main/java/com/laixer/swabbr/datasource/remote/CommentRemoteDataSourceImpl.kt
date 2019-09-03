package com.laixer.swabbr.datasource.remote

import com.laixer.swabbr.data.datasource.CommentRemoteDataSource
import com.laixer.swabbr.datasource.model.mapToDomain
import com.laixer.swabbr.domain.model.Comment
import io.reactivex.Single

class CommentRemoteDataSourceImpl constructor(
    private val api: CommentsApi
) : CommentRemoteDataSource {

    override fun get(postId: String): Single<List<Comment>> =
        api.getComments(postId)
            .map { it.mapToDomain() }
}
