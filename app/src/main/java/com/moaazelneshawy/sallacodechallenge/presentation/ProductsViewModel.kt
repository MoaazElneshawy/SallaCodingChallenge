package com.moaazelneshawy.sallacodechallenge.presentation

import androidx.lifecycle.ViewModel
import com.moaazelneshawy.sallacodechallenge.data.network.ApiService
import com.moaazelneshawy.sallacodechallenge.data.network.loadData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    fun loadProducts(page: Int) = loadData {
        apiService.getAllProducts(259940351, page, 8)
    }

    fun loadProductDetails(id: Int) = loadData {
        apiService.getProductDetails(id)
    }

}

