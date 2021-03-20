package com.zmd.sample.googleimgsearch

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

import com.zmd.lab.search.img.GoogleImgSearch
import com.zmd.lab.search.img.model.ImgInfo

class MainActivity : AppCompatActivity() {
    private val listViewModel by viewModels<ListViewModel> {
        ListViewModelFactory(ArrayList<ImgInfo>())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerAdapter = HeaderAdapter()
        val imgInfoAdapter = ImgInfoAdapter(this) { info -> adapterOnClick(info) }
        val concatAdapter = ConcatAdapter(headerAdapter, imgInfoAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        listViewModel.liveData.observe(this, {
            it?.let {
                imgInfoAdapter.submitList(it as MutableList<ImgInfo>)
                headerAdapter.updateCount(it.size)
            }
        })

        GoogleImgSearch().startSearch("cat") { imgInfoList ->
            for (info in imgInfoList) {
                Log.d("zmdlab", info.toString())
            }
            listViewModel.insertList(imgInfoList)
        }
    }

    private fun adapterOnClick(info: ImgInfo) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(info.link))
        startActivity(intent)
    }
}