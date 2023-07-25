package com.example.searchfromdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videoInfo")
data class VideoInfoData (
    @PrimaryKey @ColumnInfo(name = "videoID") val videoID : String,
    val subtitle: String
)
