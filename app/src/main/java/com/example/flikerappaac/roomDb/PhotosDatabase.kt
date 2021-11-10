package com.example.flikerappaac.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= [PhotosEntity::class], version = 1, exportSchema = false)
abstract class PhotosDatabase:RoomDatabase() {
    companion object{
        var instance:PhotosDatabase?=null
        fun getInstance(context: Context):PhotosDatabase {
            if(instance!=null){
                return instance as PhotosDatabase
            }
            instance= Room.databaseBuilder(context,PhotosDatabase::class.java, "data").run {allowMainThreadQueries()}
                .fallbackToDestructiveMigration()
                .build()
            return instance as PhotosDatabase
        }
    }
    abstract fun getPhotosDao():PhotosDao
}