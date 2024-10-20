package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.entities.CityInputEntity

@Database(entities = [CityInputEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao():AppDao

    companion object{
        private var dbInstance:AppDatabase?=null
        fun getInstance(context: Context):AppDatabase{
            return dbInstance?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "WeatherNowAndLater"
                ).fallbackToDestructiveMigration()
                    .build()
                dbInstance =instance
                instance
            }
        }
    }
}