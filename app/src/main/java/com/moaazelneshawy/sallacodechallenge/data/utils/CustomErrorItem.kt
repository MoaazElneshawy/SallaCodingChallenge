package com.moaazelneshawy.sallacodechallenge.data.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.R
import ru.alexbykov.nopaginate.callback.OnRepeatListener
import ru.alexbykov.nopaginate.item.ErrorItem


class CustomErrorItem : ErrorItem {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_paginate_error, parent, false)
        return object : RecyclerView.ViewHolder(view) {

        }
    }


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder?,
        position: Int,
        onRepeatListener: OnRepeatListener?
    ) {
        holder?.itemView?.setOnClickListener {
            onRepeatListener?.onClickRepeat()
        }
    }

}