package com.example.flikerappaac.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flikerappaac.repository.Repository
import com.example.flikerappaac.retrofit.FlickrModel
import com.example.flikerappaac.roomDb.PhotosDatabase
import com.example.flikerappaac.roomDb.PhotosEntity
import kotlinx.coroutines.launch

class ViewModel (app:Application): AndroidViewModel(app) {
    private val repository:Repository=Repository(PhotosDatabase.getInstance(app))
    private var photosList: MutableLiveData<List<FlickrModel.Photos.Photo>>?=null

    fun getPhotosData(text:String): MutableLiveData<List<FlickrModel.Photos.Photo>>? {
        photosList= repository.getPhotosData(text)
        return photosList
    }
   fun getAllDBPhotos(): LiveData<List<PhotosEntity>> {
        return repository.getAllDBPhotos() }
     fun insertDBPhoto(photosEntity: PhotosEntity){
         viewModelScope.launch { repository.insertDBPhoto(photosEntity) } }
    fun delDBPhoto(photosID: Int){
        viewModelScope.launch { repository.delDBPhoto(photosID)}}

}