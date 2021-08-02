package com.moaazelneshawy.sallacodechallenge.data.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.R
import ru.alexbykov.nopaginate.item.LoadingItem


class CustomLoadingItem : LoadingItem {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_paginate_loading, parent, false)
        return object : RecyclerView.ViewHolder(view) {

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

    }

}