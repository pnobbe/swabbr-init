@file:Suppress("IllegalIdentifier")

package com.laixer.swabbr.datasource.model

import com.laixer.swabbr.postEntity
import org.junit.Assert.assertTrue
import org.junit.Test

class PostEntityTest {

    @Test
    fun `map entity to domain`() {
        // given

        // when
        val model = postEntity.mapToDomain()

        // then
        assertTrue(model.userId == postEntity.userId)
        assertTrue(model.id == postEntity.id)
        assertTrue(model.title == postEntity.title)
        assertTrue(model.body == postEntity.body)
    }
}
