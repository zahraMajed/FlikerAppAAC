package com.example.flikerappaac.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhotosDao {
    @Query("select * from Photos")
    fun getAllDBPhotos(): LiveData<List<PhotosEntity>>
    @Insert
    fun insertDBPhoto(photo: PhotosEntity)
    @Query("Delete from Photos where ID=:photoID")
    fun delDBPhoto(photoID: Int)
}