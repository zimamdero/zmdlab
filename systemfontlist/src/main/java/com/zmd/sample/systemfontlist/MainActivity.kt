package com.zmd.sample.systemfontlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = FontListAdapter(findSystemFontList())
    }

    private fun findSystemFontList(): ArrayList<String> {
        val fontNameList = ArrayList<String>()
        val fonts = File("/system/fonts/")
        val fontSuffix = ".ttf"

        for (f in fonts.listFiles()) {
            val name = f.name

            if (name.endsWith(fontSuffix)) {
                fontNameList.add(name.subSequence(0, name.lastIndexOf(fontSuffix)).toString())
            }
        }

        return fontNameList
    }
}