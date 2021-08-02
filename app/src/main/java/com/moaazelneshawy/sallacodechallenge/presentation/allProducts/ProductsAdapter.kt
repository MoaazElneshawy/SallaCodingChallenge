package com.moaazelneshawy.sallacodechallenge.presentation.allProducts

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.data.utils.applyCustomShape
import com.moaazelneshawy.sallacodechallenge.databinding.ItemProductBinding
import com.moaazelneshawy.sallacodechallenge.domain.BrandProduct
import kotlinx.android.synthetic.main.item_product.view.*


/**
Created by Moaaz Elneshawy
on 01,August,2021
 **/

class ProductsAdapter(private val listener: OnProductClickListener) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val TAG = ProductsAdapter::class.java.simpleName
    private val products = mutableListOf<BrandProduct>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

    fun fill(products: List<BrandProduct>) {
        this.products.clear()
        this.products.addAll(0, products)
        notifyDataSetChanged()
    }

    fun addMoreProducts(products: List<BrandProduct>) {
        this.products.addAll(products)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            setCustomButtonShape()
        }

        fun bind(product: BrandProduct) {
            with(binding) {
                this.product = product
                executePendingBindings()
                root.setOnClickListener { listener.onProductClicked(product.id) }
            }
        }

        private fun setCustomButtonShape() {
            binding.root.btnAddToCart.apply {
                this.applyCustomShape(cornersRadius = 20f)
                setTextColor(Color.WHITE)
            }
        }
    }
}

interface OnProductClickListener {
    fun onProductClicked(id: Int)
}