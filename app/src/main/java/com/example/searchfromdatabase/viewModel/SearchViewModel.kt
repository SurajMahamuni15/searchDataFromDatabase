package com.example.searchfromdatabase.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.searchfromdatabase.db.ELearningDataBase
import com.example.searchfromdatabase.model.VideoInfoData
import com.example.searchfromdatabase.repository.SearchResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val searchResultRepository: SearchResultRepository
    private lateinit var list: LiveData<List<VideoInfoData>>

    init {
//        val mContext = context
        val mContext = getApplication<Application>().applicationContext
        val searchDao = ELearningDataBase.getDataBase(mContext).getCustomerDao()
        searchResultRepository = SearchResultRepository(searchDao)
    }

    fun getVideoInfo(searchItem: String): LiveData<List<VideoInfoData>> {
        return searchResultRepository.getVideoInfo(searchItem).asLiveData(Dispatchers.Main)
    }

    fun addVideoInfo(videoInfo: VideoInfoData) {
        viewModelScope.launch(Dispatchers.IO) {
            searchResultRepository.addVideoInfo(videoInfo)
        }
    }
}