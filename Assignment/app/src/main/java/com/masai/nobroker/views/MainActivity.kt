package com.masai.nobroker.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.nobroker.R
import com.masai.nobroker.data.local.MyEntity
import com.masai.nobroker.viewmodels.MyViewModel
import com.masai.nobroker.viewmodels.MyViewModelFactory
import com.masai.nobroker.views.interfaces.ItemClickListener
import com.masai.nobroker.views.adapter.PostAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),ItemClickListener{
    private lateinit var viewModel: MyViewModel
    val entity= mutableListOf<MyEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter2=PostAdapter(this@MainActivity,entity,this)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter2
        val app=application as ApplicationClass
        val viewModelFactory= MyViewModelFactory(app.repository)
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(MyViewModel::class.java)
        viewModel.getPosts().observe(this, Observer {
            entity.clear()
            entity.addAll(it)
            adapter2.notifyDataSetChanged()

        })
            viewModel.insertPosts()


    }

    override fun onItemClicked(myEntity: MyEntity) {
       Toast.makeText(this,myEntity.title,Toast.LENGTH_LONG).show()
    }
}