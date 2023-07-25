package com.example.searchfromdatabase.repository

import com.example.searchfromdatabase.dao.SearchDao
import com.example.searchfromdatabase.model.VideoInfoData
import kotlinx.coroutines.flow.Flow

class SearchResultRepository(private val searchDao: SearchDao) {

    fun getVideoInfo(searchItem : String) : Flow<List<VideoInfoData>>{
        return searchDao.getVideoInfo(searchItem)
    }

    fun addVideoInfo(videoInfo : VideoInfoData){
        searchDao.addVideoInfo(videoInfo)
    }
}