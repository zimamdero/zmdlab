package com.zmd.sample.googleimgsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var count = 0

    class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val textView: TextView = itemView.findViewById(R.id.search_count_text)

        fun bind(count: Int) {
            textView.text = count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(count)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateCount(updatedCount: Int) {
        count = updatedCount
        notifyDataSetChanged()
    }
}