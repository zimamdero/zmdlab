package com.zmd.sample.googleimgsearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zmd.lab.search.img.model.ImgInfo

class DataSource(list: ArrayList<ImgInfo>) {
    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(list: ArrayList<ImgInfo>): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(list)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    private val imgInfoList = list
    private val liveData = MutableLiveData(imgInfoList)

    fun add(info: ImgInfo) {
        val currentList = liveData.value
        if (currentList == null) {
            liveData.postValue(arrayListOf(info))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, info)
            liveData.postValue(updatedList as ArrayList<ImgInfo>?)
        }
    }

    fun remove(info: ImgInfo) {
        val currentList = liveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(info)
            liveData.postValue(updatedList as ArrayList<ImgInfo>?)
        }
    }

    fun getInfoForId(id: String): ImgInfo? {
        liveData.value?.let { infoList ->
            return infoList.firstOrNull { it.id == id }
        }
        return null
    }

    fun getInfoList(): LiveData<ArrayList<ImgInfo>> {
        return liveData
    }

}