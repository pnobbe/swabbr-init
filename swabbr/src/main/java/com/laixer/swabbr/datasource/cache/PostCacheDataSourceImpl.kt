package com.laixer.swabbr.datasource.cache

import com.laixer.cache.ReactiveCache
import com.laixer.swabbr.data.datasource.PostCacheDataSource
import com.laixer.swabbr.domain.model.Post
import io.reactivex.Single

class PostCacheDataSourceImpl constructor(
    private val cache: ReactiveCache<List<Post>>
) : PostCacheDataSource {
    val key = "Post List"

    override fun get(): Single<List<Post>> =
        cache.load(key)

    override fun set(list: List<Post>): Single<List<Post>> =
        cache.save(key, list)

    override fun get(postId: String): Single<Post> =
        cache.load(key)
            .map { it.first { it.id == postId } }

    override fun set(item: Post): Single<Post> =
        cache.load(key)
            .map { it.filter { it.id != item.id }.plus(item) }
            .flatMap { set(it) }
            .map { item }
}
