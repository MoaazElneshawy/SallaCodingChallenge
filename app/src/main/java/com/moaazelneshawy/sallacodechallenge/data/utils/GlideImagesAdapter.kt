package com.moaazelneshawy.sallacodechallenge.data.utils

import androidx.appcompat.widget.AppCompatImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.moaazelneshawy.sallacodechallenge.R


/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/


@BindingAdapter("bind:imageUrl")
fun loadImage(view: AppCompatImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_placeholder)
        .error(R.drawable.ic_error)
        .into(view)
}