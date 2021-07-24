package com.masai.nobroker.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.nobroker.R
import com.masai.nobroker.data.local.MyEntity
import com.masai.nobroker.views.interfaces.ItemClickListener
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * This class is responsible for create and bind the data in the recyclerview
 */
class PostAdapter(
    val context: Context,
    var postList: List<MyEntity>,
    val listner: ItemClickListener
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.itemView.apply {
            image.setImageBitmap(postList[position].image)
            title.text = postList[position].title
            subTitle.text = postList[position].subTitle
        }
        holder.itemView.setOnClickListener {
            listner.onItemClicked(postList[position])
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setData(newData: List<MyEntity>) {
        postList = newData
        notifyDataSetChanged()
    }
}