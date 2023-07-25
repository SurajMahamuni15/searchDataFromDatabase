package com.example.searchfromdatabase.dao

import androidx.room.*
import com.example.searchfromdatabase.model.VideoInfoData
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    @Query("select * from videoInfo where subtitle like :searchItem ")
    fun getVideoInfo(searchItem : String) : Flow<List<VideoInfoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addVideoInfo(videoInfo : VideoInfoData)

}