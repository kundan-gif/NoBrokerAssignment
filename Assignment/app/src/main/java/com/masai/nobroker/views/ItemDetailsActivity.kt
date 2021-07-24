package com.masai.nobroker.views

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.masai.nobroker.R
import kotlinx.android.synthetic.main.activity_item_details.*


class ItemDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        setupActionBar()
        statuscolor()
        if(intent.extras!=null){
            val byteArray = intent.getByteArrayExtra("imageKey")
            val bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
            ivImage.setImageBitmap(bmp)
            tvTitle.text=intent.getStringExtra("titleKey")
            tvSubTitle.text=intent.getStringExtra("subTitleKey")
        }
    }
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {
        setSupportActionBar(toolbar_item_details_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }

        toolbar_item_details_activity.setNavigationOnClickListener { onBackPressed() }
    }
    fun statuscolor() {
        if (Build.VERSION.SDK_INT >= 23) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark)
            getWindow().decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}