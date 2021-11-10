package com.example.flikerappaac.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("/services/rest/?method=flickr.photos.search&api_key=12b2987803e90b744de549c7607ed75a&format=json&nojsoncallback=1")
    fun getPhotosData(@Query("text")text:String): Call<FlickrModel>
}