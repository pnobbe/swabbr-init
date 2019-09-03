package com.laixer.swabbr.datasource.cache

import com.laixer.cache.ReactiveCache
import com.laixer.swabbr.data.datasource.CommentCacheDataSource
import com.laixer.swabbr.domain.model.Comment
import io.reactivex.Single

class CommentCacheDataSourceImpl constructor(
    private val cache: ReactiveCache<List<Comment>>
) : CommentCacheDataSource {

    val key = "Comment List"

    override fun get(postId: String): Single<List<Comment>> =
        cache.load(key + postId)

    override fun set(postId: String, list: List<Comment>): Single<List<Comment>> =
        cache.save(key + postId, list)
}
