package com.laixer.swabbr

import com.laixer.cache.ReactiveCache
import com.laixer.network.createNetworkClient
import com.laixer.swabbr.data.datasource.CommentCacheDataSource
import com.laixer.swabbr.data.datasource.CommentRemoteDataSource
import com.laixer.swabbr.data.datasource.PostCacheDataSource
import com.laixer.swabbr.data.datasource.PostRemoteDataSource
import com.laixer.swabbr.data.datasource.UserCacheDataSource
import com.laixer.swabbr.data.datasource.UserRemoteDataSource
import com.laixer.swabbr.data.repository.CommentRepositoryImpl
import com.laixer.swabbr.data.repository.PostRepositoryImpl
import com.laixer.swabbr.data.repository.UserRepositoryImpl
import com.laixer.swabbr.datasource.cache.CommentCacheDataSourceImpl
import com.laixer.swabbr.datasource.cache.PostCacheDataSourceImpl
import com.laixer.swabbr.datasource.cache.UserCacheDataSourceImpl
import com.laixer.swabbr.datasource.model.PostEntity
import com.laixer.swabbr.datasource.model.UserEntity
import com.laixer.swabbr.datasource.remote.CommentRemoteDataSourceImpl
import com.laixer.swabbr.datasource.remote.CommentsApi
import com.laixer.swabbr.datasource.remote.PostRemoteDataSourceImpl
import com.laixer.swabbr.datasource.remote.PostsApi
import com.laixer.swabbr.datasource.remote.UserRemoteDataSourceImpl
import com.laixer.swabbr.datasource.remote.UsersApi
import com.laixer.swabbr.domain.model.Comment
import com.laixer.swabbr.domain.repository.CommentRepository
import com.laixer.swabbr.domain.repository.PostRepository
import com.laixer.swabbr.domain.repository.UserRepository
import com.laixer.swabbr.domain.usecase.CommentsUseCase
import com.laixer.swabbr.domain.usecase.UserPostUseCase
import com.laixer.swabbr.domain.usecase.UsersPostsUseCase
import com.laixer.swabbr.presentation.postdetails.PostDetailsViewModel
import com.laixer.swabbr.presentation.postlist.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        cacheModule
    )
}

val viewModelModule: Module = module {
    viewModel { PostListViewModel(usersPostsUseCase = get()) }
    viewModel { PostDetailsViewModel(userPostUseCase = get(), commentsUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { UsersPostsUseCase(userRepository = get(), postRepository = get()) }
    factory { UserPostUseCase(userRepository = get(), postRepository = get()) }
    factory { CommentsUseCase(commentRepository = get()) }
}

val repositoryModule: Module = module {
    single { UserRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as UserRepository }
    single { PostRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as PostRepository }
    single { CommentRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as CommentRepository }
}

val dataSourceModule: Module = module {
    single { UserCacheDataSourceImpl(cache = get(USER_CACHE)) as UserCacheDataSource }
    single { UserRemoteDataSourceImpl(api = usersApi) as UserRemoteDataSource }
    single { PostCacheDataSourceImpl(cache = get(POST_CACHE)) as PostCacheDataSource }
    single { PostRemoteDataSourceImpl(api = postsApi) as PostRemoteDataSource }
    single { CommentCacheDataSourceImpl(cache = get(COMMENT_CACHE)) as CommentCacheDataSource }
    single { CommentRemoteDataSourceImpl(api = commentsApi) as CommentRemoteDataSource }
}

val networkModule: Module = module {
    single { usersApi }
    single { postsApi }
    single { commentsApi }
}

val cacheModule: Module = module {
    single(name = USER_CACHE) { ReactiveCache<List<UserEntity>>() }
    single(name = POST_CACHE) { ReactiveCache<List<PostEntity>>() }
    single(name = COMMENT_CACHE) { ReactiveCache<List<Comment>>() }
}

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)

private val postsApi: PostsApi = retrofit.create(PostsApi::class.java)
private val usersApi: UsersApi = retrofit.create(UsersApi::class.java)
private val commentsApi: CommentsApi = retrofit.create(CommentsApi::class.java)

private const val USER_CACHE = "USER_CACHE"
private const val POST_CACHE = "POST_CACHE"
private const val COMMENT_CACHE = "COMMENT_CACHE"