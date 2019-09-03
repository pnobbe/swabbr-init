@file:Suppress("IllegalIdentifier")

package com.laixer.swabbr.presentation.model

import com.laixer.swabbr.domain.usecase.CombinedUserPost
import com.laixer.swabbr.post
import com.laixer.swabbr.user
import org.junit.Assert.assertTrue
import org.junit.Test

class PostItemTest {

    @Test
    fun `map domain to presentation`() {
        // given
        val combinedUserPost = CombinedUserPost(user, post)

        // when
        val postItem = combinedUserPost.mapToPresentation()

        // then
        assertTrue(postItem.postId == post.id)
        assertTrue(postItem.userId == user.id)
        assertTrue(postItem.title == post.title)
        assertTrue(postItem.body == post.body)
        assertTrue(postItem.name == user.name)
        assertTrue(postItem.username == user.username)
        assertTrue(postItem.email == user.email)
    }
}
