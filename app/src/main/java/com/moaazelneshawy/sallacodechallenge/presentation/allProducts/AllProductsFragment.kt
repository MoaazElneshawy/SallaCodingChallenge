package com.moaazelneshawy.sallacodechallenge.presentation.allProducts

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.R
import com.moaazelneshawy.sallacodechallenge.data.utils.applyCustomShape
import com.moaazelneshawy.sallacodechallenge.databinding.AllProductsFragmentBinding
import com.moaazelneshawy.sallacodechallenge.presentation.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllProductsFragment : Fragment() {

    private val viewModel by viewModels<ProductsViewModel>()
    private val productsAdapter by lazy { ProductsAdapter() }
    private lateinit var binding: AllProductsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.all_products_fragment,
            container,
            false
        )
        binding.cvBanner.applyCustomShape(backgroundColor = Color.WHITE, cornersRadius = 20f)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadProducts().observe(viewLifecycleOwner) {
            it?.data?.let {
                productsAdapter.addProducts(it.data)
                binding.brand = it.brand
                binding.rvProducts.apply {
                    layoutManager =
                        GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
                    this.adapter = productsAdapter
                }
            }
        }

    }


}