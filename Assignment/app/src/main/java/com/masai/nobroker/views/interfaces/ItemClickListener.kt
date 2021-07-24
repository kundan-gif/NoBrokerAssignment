package com.masai.nobroker.views.interfaces

import com.masai.nobroker.data.local.MyEntity

interface ItemClickListener {
    fun onItemClicked(myEntity: MyEntity)
}