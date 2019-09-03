package com.laixer.swabbr.datasource.remote

import com.laixer.swabbr.data.datasource.PostRemoteDataSource
import com.laixer.swabbr.datasource.model.mapToDomain
import com.laixer.swabbr.domain.model.Post
import io.reactivex.Single

class PostRemoteDataSourceImpl constructor(
    private val api: PostsApi
) : PostRemoteDataSource {

    override fun get(): Single<List<Post>> =
        api.getPosts()
            .map { it.mapToDomain() }

    override fun get(postId: String): Single<Post> =
        api.getPost(postId)
            .map { it.mapToDomain() }
}
