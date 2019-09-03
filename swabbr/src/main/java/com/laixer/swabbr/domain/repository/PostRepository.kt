package com.laixer.swabbr.domain.repository

import com.laixer.swabbr.domain.model.Post
import io.reactivex.Single

interface PostRepository {

    fun get(refresh: Boolean): Single<List<Post>>

    fun get(postId: String, refresh: Boolean): Single<Post>
}
