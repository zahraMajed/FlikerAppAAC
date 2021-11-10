package com.example.flikerappaac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flikerappaac.MainActivity
import com.example.flikerappaac.R
import com.example.flikerappaac.retrofit.FlickrModel
import kotlinx.android.synthetic.main.recycler_item.view.*

class PhotosRecyclerAdapter(private val activity: MainActivity,private val photoList: List<FlickrModel.Photos.Photo>):RecyclerView.Adapter<PhotosRecyclerAdapter.PhotoItemView>() {
    class PhotoItemView (itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemView {
        return PhotoItemView(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoItemView, position: Int) {
        val photoObj=photoList[position]
        val photoTitle=photoList[position].title
        val farmID = photoList[position].farm
        val serverID = photoList[position].server
        val id = photoList[position].id
        val secret = photoList[position].secret
        val photoLink = "https://farm$farmID.staticflickr.com/$serverID/${id}_$secret.jpg"
        holder.itemView.apply {
            tvPhotoTitle.text=photoTitle
            btnAdd.visibility=View.VISIBLE
            Glide.with(this).load(photoLink).into(imgFlickrPhoto)
            imgFlickrPhoto.setOnClickListener{
                activity.openFullImage(photoLink)
            }
            btnAdd.setOnClickListener(){
                activity.insertDBPhoto(photoTitle,photoLink)
            }
        }
    }

    override fun getItemCount(): Int = photoList.size

}