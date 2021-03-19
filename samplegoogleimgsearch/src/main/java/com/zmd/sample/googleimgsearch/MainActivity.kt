package com.zmd.sample.googleimgsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.zmd.lab.search.img.GoogleImgSearch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GoogleImgSearch().startSearch("cat") { imgInfoList ->
            for (info in imgInfoList) {
                Log.d("zmdlab", info.toString())
            }
        }
    }
}