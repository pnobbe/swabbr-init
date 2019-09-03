package com.laixer.swabbr.data.repository

import com.laixer.swabbr.data.datasource.PostCacheDataSource
import com.laixer.swabbr.data.datasource.PostRemoteDataSource
import com.laixer.swabbr.domain.model.Post
import com.laixer.swabbr.domain.repository.PostRepository
import io.reactivex.Single

class PostRepositoryImpl constructor(
    private val cacheDataSource: PostCacheDataSource,
    private val remoteDataSource: PostRemoteDataSource
) : PostRepository {

    override fun get(refresh: Boolean): Single<List<Post>> =
        when (refresh) {
            true -> remoteDataSource.get()
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get()
                .onErrorResumeNext { get(true) }
        }

    override fun get(postId: String, refresh: Boolean): Single<Post> =
        when (refresh) {
            true -> remoteDataSource.get(postId)
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get(postId)
                .onErrorResumeNext { get(postId, true) }
        }
}
