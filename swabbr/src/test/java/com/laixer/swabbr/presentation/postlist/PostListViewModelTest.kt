@file:Suppress("IllegalIdentifier")

package com.laixer.swabbr.presentation.postlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.laixer.swabbr.combinedUserPost
import com.laixer.swabbr.domain.usecase.UsersPostsUseCase
import com.laixer.swabbr.presentation.RxSchedulersOverrideRule
import com.laixer.swabbr.presentation.model.mapToPresentation
import com.laixer.presentation.Resource
import com.laixer.presentation.ResourceState
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PostListViewModelTest {

    private lateinit var viewModel: PostListViewModel

    private val mockUseCase: UsersPostsUseCase = mock()

    private val combinedUserPostList = listOf(combinedUserPost)
    private val throwable = Throwable()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = PostListViewModel(mockUseCase)
    }

    @Test
    fun `get post item list succeeds`() {
        // given
        whenever(mockUseCase.get(false)).thenReturn(Single.just(combinedUserPostList))

        // when
        viewModel.get(false)

        // then
        verify(mockUseCase).get(false)
        assertEquals(
            Resource(ResourceState.SUCCESS, combinedUserPostList.mapToPresentation(), null),
            viewModel.posts.value
        )
    }

    @Test
    fun `get post item list fails`() {
        // given
        whenever(mockUseCase.get(false)).thenReturn(Single.error(throwable))

        // when
        viewModel.get(false)

        // then
        verify(mockUseCase).get(false)
        assertEquals(
            Resource(ResourceState.ERROR, null, throwable.message),
            viewModel.posts.value
        )
    }
}
