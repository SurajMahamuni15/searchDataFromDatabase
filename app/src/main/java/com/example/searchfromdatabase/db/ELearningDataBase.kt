package com.example.searchfromdatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.searchfromdatabase.dao.SearchDao
import com.example.searchfromdatabase.model.VideoInfoData


@Database(entities = [VideoInfoData::class], version = 1,exportSchema = false )
abstract class ELearningDataBase : RoomDatabase() {
    abstract fun getCustomerDao(): SearchDao

    companion object {
        private var INSTANCE: ELearningDataBase? = null
        fun getDataBase(context: Context): ELearningDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ELearningDataBase::class.java,

                    "e_learningDB"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }

}
