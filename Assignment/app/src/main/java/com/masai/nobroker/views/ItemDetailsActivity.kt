package com.masai.nobroker.views

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.masai.nobroker.R
import kotlinx.android.synthetic.main.activity_item_details.*


class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        if(intent.extras!=null){
            val byteArray = intent.getByteArrayExtra("imageKey")
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
            ivImage.setImageBitmap(bmp)
            tvTitle.text=intent.getStringExtra("titleKey")
            tvSubTitle.text=intent.getStringExtra("subTitleKey")
        }
    }
}