package com.moaazelneshawy.sallacodechallenge.presentation.allProducts

import android.R.attr.left
import android.R.attr.right
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.data.utils.applyCustomShape
import com.moaazelneshawy.sallacodechallenge.databinding.ItemProductBinding
import com.moaazelneshawy.sallacodechallenge.domain.BrandProduct
import kotlinx.android.synthetic.main.item_product.view.*


/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/

class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val TAG = ProductsAdapter::class.java.simpleName
    private val products = mutableListOf<BrandProduct>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

    fun addProducts(products: List<BrandProduct>) {
        this.products.clear()
        this.products.addAll(0, products)
        notifyDataSetChanged()
    }

    fun addMoreProducts(products: List<BrandProduct>) {
        this.products.addAll(0, products)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            setItemMargins()
            setCustomButtonShape()
        }

        private fun setItemMargins() {
            val params = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 8, 8, 8)
//            binding.root.layoutParams = params
        }

        fun bind(product: BrandProduct) {
            with(binding) {
                this.product = product
                executePendingBindings()
            }
        }

        private fun setCustomButtonShape() {

            binding.root.btnAddToCart.apply {
                this.applyCustomShape(cornersRadius = 20f)
                setTextColor(Color.WHITE)
                setOnClickListener { Log.e(TAG, "add to Cart !") }
            }

        }
    }
}