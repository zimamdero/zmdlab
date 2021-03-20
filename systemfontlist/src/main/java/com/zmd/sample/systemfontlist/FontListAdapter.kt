package com.zmd.sample.systemfontlist

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FontListAdapter(private val dataSet: ArrayList<String>):
    RecyclerView.Adapter<FontListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text_view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val font = dataSet[position]
        holder.textView.text = font
        holder.textView.setTypeface(Typeface.createFromFile("system/fonts/$font.ttf"))
    }

    override fun getItemCount() = dataSet.size
}