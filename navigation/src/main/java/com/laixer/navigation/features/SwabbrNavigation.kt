package com.laixer.navigation.features

import android.content.Intent
import com.laixer.navigation.loadIntentOrNull

object SwabbrNavigation : DynamicFeature<Intent> {

    const val USER_ID_KEY = "USER_ID_KEY"
    const val POST_ID_KEY = "POST_ID_KEY"

    private const val POST_LIST = "com.laixer.swabbr.presentation.postlist.PostListActivity"
    private const val POST_DETAILS = "com.laixer.swabbr.presentation.postdetails.PostDetailsActivity"

    override val dynamicStart: Intent?
        get() = POST_LIST.loadIntentOrNull()

    fun postDetails(userId: String, postId: String): Intent? =
        POST_DETAILS.loadIntentOrNull()
            ?.apply {
                putExtra(USER_ID_KEY, userId)
                putExtra(POST_ID_KEY, postId)
            }
}