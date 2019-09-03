package com.laixer.swabbr.presentation.postdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laixer.swabbr.domain.usecase.CommentsUseCase
import com.laixer.swabbr.domain.usecase.UserPostUseCase
import com.laixer.swabbr.presentation.model.CommentItem
import com.laixer.swabbr.presentation.model.PostItem
import com.laixer.swabbr.presentation.model.mapToPresentation
import com.laixer.presentation.Resource
import com.laixer.presentation.setError
import com.laixer.presentation.setLoading
import com.laixer.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class UserIdPostId(val userId: String, val postId: String)

class PostDetailsViewModel constructor(
    private val userPostUseCase: UserPostUseCase,
    private val commentsUseCase: CommentsUseCase
) : ViewModel() {

    val post = MutableLiveData<PostItem>()
    val comments = MutableLiveData<Resource<List<CommentItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getPost(ids: UserIdPostId) =
        compositeDisposable.add(userPostUseCase.get(ids.userId, ids.postId, false)
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ post.postValue(it) }, { })
        )

    fun getComments(postId: String, refresh: Boolean = false) =
        compositeDisposable.add(commentsUseCase.get(postId, refresh)
            .doOnSubscribe { comments.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ comments.setSuccess(it) }, { comments.setError(it.message) })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
