package com.zmd.sample.googleimgsearch

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zmd.lab.search.img.model.ImgInfo
import java.net.URL

class ImgInfoAdapter(private val activity: Activity, private val onClick: (ImgInfo) -> Unit) :
    ListAdapter<ImgInfo, ImgInfoAdapter.ImgInfoViewHolder>(ImgInfoDiffCallback) {

    class ImgInfoViewHolder(itemView: View, activity: Activity, val onClick: (ImgInfo) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.title)
        private val imgView: ImageView = itemView.findViewById(R.id.img)
        private var currentInfo: ImgInfo? = null
        private val activity = activity

        init {
            itemView.setOnClickListener {
                currentInfo?.let {
                    onClick(it)
                }
            }
        }

        fun bind(info: ImgInfo) {
            currentInfo = info
            textView.text = info.title
            if (info.origin.isNotEmpty()) {
                loadImg(info.origin) {
                    activity.runOnUiThread(Runnable {
                        imgView.setImageBitmap(it)
                    })
                }
            }
        }

        private fun loadImg(origin: String, callback: (bitmap: Bitmap) -> Unit) {
            Thread(Runnable {
                val url = URL(origin)
                val bitmap = BitmapFactory.decodeStream(url.openStream())
                callback(bitmap)
            }).start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ImgInfoViewHolder(view, activity, onClick)
    }

    override fun onBindViewHolder(holder: ImgInfoViewHolder, position: Int) {
        val info = getItem(position)
        holder.bind(info)
    }
}

object ImgInfoDiffCallback: DiffUtil.ItemCallback<ImgInfo>() {
    override fun areItemsTheSame(oldItem: ImgInfo, newItem: ImgInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ImgInfo, newItem: ImgInfo): Boolean {
        return oldItem.id == newItem.id
    }
}