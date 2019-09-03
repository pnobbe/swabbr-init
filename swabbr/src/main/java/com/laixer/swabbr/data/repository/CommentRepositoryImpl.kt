package com.laixer.swabbr.data.repository

import com.laixer.swabbr.data.datasource.CommentCacheDataSource
import com.laixer.swabbr.data.datasource.CommentRemoteDataSource
import com.laixer.swabbr.domain.model.Comment
import com.laixer.swabbr.domain.repository.CommentRepository
import io.reactivex.Single

class CommentRepositoryImpl constructor(
    private val cacheDataSource: CommentCacheDataSource,
    private val remoteDataSource: CommentRemoteDataSource
) : CommentRepository {

    override fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        when (refresh) {
            true -> remoteDataSource.get(postId)
                .flatMap { cacheDataSource.set(postId, it) }
            false -> cacheDataSource.get(postId)
                .onErrorResumeNext { get(postId, true) }
        }
}
