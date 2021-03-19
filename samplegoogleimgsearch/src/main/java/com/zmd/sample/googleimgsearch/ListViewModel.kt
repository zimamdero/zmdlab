package com.zmd.sample.googleimgsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zmd.lab.search.img.model.ImgInfo

class ListViewModel(val dataSource: DataSource): ViewModel() {
    val liveData = dataSource.getInfoList()

    fun insert(info: ImgInfo) {
        if (info == null) {
            return
        }

        dataSource.add(info)
    }
}

class ListViewModelFactory(private val list: ArrayList<ImgInfo>): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(
                dataSource = DataSource.getDataSource(list)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}