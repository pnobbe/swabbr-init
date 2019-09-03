package com.laixer.swabbr.presentation.postlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laixer.swabbr.domain.usecase.UsersPostsUseCase
import com.laixer.swabbr.presentation.model.PostItem
import com.laixer.swabbr.presentation.model.mapToPresentation
import com.laixer.presentation.Resource
import com.laixer.presentation.setError
import com.laixer.presentation.setLoading
import com.laixer.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostListViewModel constructor(private val usersPostsUseCase: UsersPostsUseCase) :
    ViewModel() {

    val posts = MutableLiveData<Resource<List<PostItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun get(refresh: Boolean = false) =
        compositeDisposable.add(usersPostsUseCase.get(refresh)
            .doOnSubscribe { posts.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ posts.setSuccess(it) }, { posts.setError(it.message) })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
