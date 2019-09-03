package com.laixer.swabbr.data.datasource

import com.laixer.swabbr.domain.model.Post
import io.reactivex.Single

interface PostCacheDataSource {

    fun get(): Single<List<Post>>

    fun set(list: List<Post>): Single<List<Post>>

    fun get(postId: String): Single<Post>

    fun set(item: Post): Single<Post>
}

interface PostRemoteDataSource {

    fun get(): Single<List<Post>>

    fun get(postId: String): Single<Post>
}
