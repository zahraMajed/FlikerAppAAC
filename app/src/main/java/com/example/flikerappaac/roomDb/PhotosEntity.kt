package com.example.flikerappaac.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Photos")
data class PhotosEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id:Int =0,
    @ColumnInfo(name = "PhotoTitle")
    val photoTitle:String,
    @ColumnInfo(name = "PhotoLink")
    val photoLink:String
)
