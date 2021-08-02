package com.moaazelneshawy.sallacodechallenge.presentation.productDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.databinding.ItemProductSliderBinding
import com.moaazelneshawy.sallacodechallenge.domain.ProductDetailsRsm

/**
Created by Moaaz Elneshawy
on 02,August,2021
 **/


class SliderAdapter(private val product: ProductDetailsRsm) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(product, position)
    }

    override fun getItemCount() = product.images.size


    inner class ViewHolder(private val binding: ItemProductSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductDetailsRsm, position: Int) {
            with(binding) {
                this.product = product
                this.position = position
            }
        }
    }
}