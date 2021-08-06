package com.moaazelneshawy.sallacodechallenge.presentation.allProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.R
import com.moaazelneshawy.sallacodechallenge.databinding.AllProductsFragmentBinding
import com.moaazelneshawy.sallacodechallenge.domain.Brand
import com.moaazelneshawy.sallacodechallenge.domain.BrandProduct
import com.moaazelneshawy.sallacodechallenge.presentation.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllProductsFragment : Fragment(), OnProductClickListener {

    private val viewModel by viewModels<ProductsViewModel>()

    private val productsAdapter by lazy { ProductsAdapter(this) }

    private val products = mutableListOf<BrandProduct>()
    private lateinit var brand: Brand

    private var load = true
    private var perPage = 5
    private var page = 1
    private var hasMoreItems = true
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
        with(binding) {
            ViewCompat.setNestedScrollingEnabled(rvProducts, false)
            nsvRoot.viewTreeObserver?.addOnScrollChangedListener {

                val view = nsvRoot.getChildAt(nsvRoot.childCount - 1)

                val diff = view.bottom - (nsvRoot.height + nsvRoot.scrollY)

                if (diff == 0)
                    if (hasMoreItems)
                        loadMoreProducts(page)
            }
        }

        binding.rvProducts.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            this.adapter = productsAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (load) getBrandProducts() else setSavedData(brand)
    }

    private fun setSavedData(brand: Brand) {
        binding.brand = brand
        productsAdapter.fill(products)
    }

    override fun onResume() {
        super.onResume()
        load = false
    }

    private fun getBrandProducts() {
        this.page = 1
        viewModel.loadProducts(page, perPage).observe(viewLifecycleOwner) {
            it?.response?.let {
                this.brand = it.brand!!
                binding.brand = it.brand
                products.addAll(it.data)
                productsAdapter.fill(products)

                it.cursor?.let { cursor ->
                    hasMoreItems = cursor.next != null
                    if (hasMoreItems) page += 1
                }

            }
        }
    }

    private fun loadMoreProducts(page: Int) {
        viewModel.loadProducts(page, 5).observe(viewLifecycleOwner) {
            it?.response?.let {
                if (it.success) {
                    hasMoreItems = it.cursor?.next != null
                    if (hasMoreItems) this.page = it.cursor?.current?.plus(1) ?: 1
                    products.addAll(it.data)
                    productsAdapter.addMoreProducts(it.data)
                } else {
                    this.page -= 1
                }
            }
        }
    }

    override fun onProductClicked(id: Int) {
        findNavController().navigate(
            R.id.actionShowProductDetails,
            bundleOf(getString(R.string.nav_key_product_id) to id)
        )
    }


}