package com.example.flikerappaac

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.flikerappaac.adapter.PhotosRecyclerAdapter
import com.example.flikerappaac.retrofit.FlickrModel
import com.example.flikerappaac.roomDb.PhotosEntity
import com.example.flikerappaac.viewModel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var etSearch:EditText
    private lateinit var btnSearch:Button
    private lateinit var btnViewDB:Button
    private lateinit var imgViewOpen:ImageView
    private lateinit var etBtnSearchLinerarL:LinearLayout
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch=findViewById(R.id.etSearch)
        btnSearch=findViewById(R.id.btnSearch)
        btnViewDB=findViewById(R.id.btnViewDBImg)
        imgViewOpen=findViewById(R.id.imgViewOpen)
        etBtnSearchLinerarL=findViewById(R.id.LL1)
        viewModel= ViewModelProvider(this)[ViewModel::class.java]

        btnSearch.setOnClickListener{
            if(etSearch.text.isNotEmpty()){
                getPhotosData()
            }else
                Toast.makeText(applicationContext, "Please fill missing entries", Toast.LENGTH_SHORT).show() }

        btnViewDB.setOnClickListener{
            intent= Intent(this,ViewDBPhotos::class.java)
            startActivity(intent)
        }
        imgViewOpen.setOnClickListener{
            closeFullPhoto()
        }
    }

    private fun getPhotosData() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                viewModel.getPhotosData(etSearch.text.toString())!!.observe(this@MainActivity,{
                    if (!it.isNullOrEmpty()){
                        rv_main.adapter= PhotosRecyclerAdapter(this@MainActivity,it)
                        rv_main.layoutManager=LinearLayoutManager(this@MainActivity)
                    }else
                        Toast.makeText(applicationContext, "No data!", Toast.LENGTH_SHORT).show()
                })
            }}
    }

    fun openFullImage(photoLink:String){
        Glide.with(this).load(photoLink).into(imgViewOpen)
        imgViewOpen.visibility= View.VISIBLE
        rv_main.visibility=View.GONE
        etBtnSearchLinerarL.visibility=View.GONE
    }

    private fun closeFullPhoto() {
        imgViewOpen.visibility= View.GONE
        rv_main.visibility=View.VISIBLE
        etBtnSearchLinerarL.visibility=View.VISIBLE
    }

    fun insertDBPhoto(photoTitle:String, photoLink: String) {
        viewModel.insertDBPhoto(PhotosEntity(0,photoTitle,photoLink))
        Toast.makeText(applicationContext, "$photoTitle added successfully", Toast.LENGTH_SHORT).show()
    }

}