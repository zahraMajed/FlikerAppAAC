package com.example.flikerappaac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flikerappaac.adapter.DBPhotosRecyclerAdapter
import com.example.flikerappaac.viewModel.ViewModel
import kotlinx.android.synthetic.main.activity_view_dbphotos.*

class ViewDBPhotos : AppCompatActivity() {
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_dbphotos)
        viewModel= ViewModelProvider(this)[ViewModel::class.java]
        getdbPhotos()
    }

    private fun getdbPhotos() {
        viewModel.getAllDBPhotos().observe(this,{
            if(!it.isNullOrEmpty()){
                rv_dbPhotos.adapter=DBPhotosRecyclerAdapter(it)
                rv_dbPhotos.layoutManager=LinearLayoutManager(this)
            }else
                Toast.makeText(applicationContext, "You haven't add photo yet!", Toast.LENGTH_SHORT).show()
        })
    }
}