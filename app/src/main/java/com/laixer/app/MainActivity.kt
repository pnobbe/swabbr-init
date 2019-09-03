package com.laixer.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.laixer.navigation.features.SwabbrNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPosts()
    }

    private fun startPosts() = SwabbrNavigation.dynamicStart?.let { startActivity(it) }
}
