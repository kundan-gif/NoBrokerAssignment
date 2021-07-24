package com.masai.nobroker.views

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.masai.nobroker.R
import com.masai.nobroker.data.local.MyEntity
import com.masai.nobroker.viewmodels.MyViewModel
import com.masai.nobroker.viewmodels.MyViewModelFactory
import com.masai.nobroker.views.adapter.PostAdapter
import com.masai.nobroker.views.interfaces.ItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity(),ItemClickListener,SearchView.OnQueryTextListener{
    private lateinit var viewModel: MyViewModel
    lateinit var adapter2: PostAdapter
    val entity= mutableListOf<MyEntity>()
    lateinit var shrimmerView: ShimmerFrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shrimmerView=findViewById(R.id.shimmer_view_container)
        adapter2=PostAdapter(this@MainActivity,entity,this)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter2
         val app = application as ApplicationClass
         val viewModelFactory = MyViewModelFactory(app.repository)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyViewModel::class.java)
            viewModel.getPosts().observe(this, Observer {
                Handler(Looper.getMainLooper()).postDelayed({
                    entity.clear()
                    entity.addAll(it)
                    adapter2.notifyDataSetChanged()
                    shrimmerView.stopShimmer()
                    shrimmerView.visibility=View.GONE
                },3000)
            })

        CoroutineScope(Dispatchers.IO).launch {
            if(viewModel.getCount()==0) {
                viewModel.insertPosts()
            }
        }
    }

    override fun onItemClicked(myEntity: MyEntity) {
        val stream = ByteArrayOutputStream()
        myEntity.image.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray: ByteArray = stream.toByteArray()
       val intent=Intent(this@MainActivity,ItemDetailsActivity::class.java)
        intent.putExtra("imageKey",byteArray)
        intent.putExtra("titleKey",myEntity.title)
        intent.putExtra("subTitleKey",myEntity.subTitle)
        startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            searchDatabase(query)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchDatabase(searchQuery).observe(this, { list ->
            list.let {
                adapter2.setData(it)
            }
        })
    }


}