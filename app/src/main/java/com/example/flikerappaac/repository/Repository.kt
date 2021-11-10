package com.example.flikerappaac.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flikerappaac.retrofit.APIClint
import com.example.flikerappaac.retrofit.APIInterface
import com.example.flikerappaac.retrofit.FlickrModel
import com.example.flikerappaac.roomDb.PhotosDatabase
import com.example.flikerappaac.roomDb.PhotosEntity
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val photosDB:PhotosDatabase ) {

    private val apiInterface:APIInterface=APIClint().getClient()!!.create(APIInterface::class.java)
    val photosList=MutableLiveData<List<FlickrModel.Photos.Photo>>()
    val getPhotosDataErrorMsg=MutableLiveData<String>()

    fun getPhotosData(text:String): MutableLiveData<List<FlickrModel.Photos.Photo>> {
        apiInterface.getPhotosData(text).enqueue(object : Callback<FlickrModel?> {
            override fun onResponse(call: Call<FlickrModel?>, response: Response<FlickrModel?>) {
                val response = response.body()!!.photos!!.photo
                photosList.value=response }
            override fun onFailure(call: Call<FlickrModel?>, t: Throwable) {
                getPhotosDataErrorMsg.value="Fail to get photos1" }
        })
        return photosList
    }
    fun getAllDBPhotos(): LiveData<List<PhotosEntity>> {
        return photosDB.getPhotosDao().getAllDBPhotos() }
    suspend fun insertDBPhoto(photosEntity: PhotosEntity){
        photosDB.getPhotosDao().insertDBPhoto(photosEntity) }
    suspend fun delDBPhoto(photosID: Int){
        photosDB.getPhotosDao().delDBPhoto(photosID) }

}