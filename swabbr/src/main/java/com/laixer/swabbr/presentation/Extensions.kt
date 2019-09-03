package com.laixer.swabbr.presentation

import android.widget.ImageView
import com.laixer.presentation.loadImageRound

fun ImageView.loadAvatar(email: String) =
    loadImageRound("https://api.adorable.io/avatars/285/$email")