package com.example.flikerappaac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flikerappaac.R
import com.example.flikerappaac.ViewDBPhotos
import com.example.flikerappaac.retrofit.FlickrModel
import com.example.flikerappaac.roomDb.PhotosEntity
import kotlinx.android.synthetic.main.recycler_item.view.*

class DBPhotosRecyclerAdapter(private val bdPhotoList: List<PhotosEntity>) :RecyclerView.Adapter<DBPhotosRecyclerAdapter.DBPhotoItemView>() {
    class DBPhotoItemView (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBPhotoItemView {
        return DBPhotoItemView(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }
    override fun onBindViewHolder(holder: DBPhotoItemView, position: Int) {
        val bdPhotoTitle=bdPhotoList[position].photoTitle
        val bdPhotoLink=bdPhotoList[position].photoLink
        holder.itemView.apply {
            tvPhotoTitle.text=bdPhotoTitle
            Glide.with(context).load(bdPhotoLink).into(imgFlickrPhoto)
            btnAdd.visibility=View.GONE
        }
    }

    override fun getItemCount(): Int = bdPhotoList.size
}