package com.moaazelneshawy.sallacodechallenge.presentation.allProducts

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.R
import com.moaazelneshawy.sallacodechallenge.data.utils.CustomErrorItem
import com.moaazelneshawy.sallacodechallenge.data.utils.CustomLoadingItem
import com.moaazelneshawy.sallacodechallenge.data.utils.applyCustomShape
import com.moaazelneshawy.sallacodechallenge.data.utils.log
import com.moaazelneshawy.sallacodechallenge.databinding.AllProductsFragmentBinding
import com.moaazelneshawy.sallacodechallenge.domain.Cursor
import com.moaazelneshawy.sallacodechallenge.presentation.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.alexbykov.nopaginate.paginate.NoPaginate


@AndroidEntryPoint
class AllProductsFragment : Fragment(), OnProductClickListener {

    private val viewModel by viewModels<ProductsViewModel>()
    private val productsAdapter by lazy { ProductsAdapter(this) }

    private var page = 1
    private lateinit var noPaginate: NoPaginate
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

    override fun onResume() {
        super.onResume()
        getBrandProducts()
    }

    private fun getBrandProducts() {
        this.page = 1
        viewModel.loadProducts(page).observe(viewLifecycleOwner) {
            it?.response?.let {
                binding.brand = it.brand

                productsAdapter.fill(it.data)

                binding.rvProducts.apply {
                    layoutManager =
                        GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
                    this.adapter = productsAdapter
                }
                it.cursor?.let { cursor ->
                    setupPagination(cursor)
                    noPaginate.showLoading(false)
                }
            }
        }
    }

    private fun setupPagination(cursor: Cursor) {
        noPaginate = NoPaginate.with(binding.rvProducts)
            .setOnLoadMoreListener {
                loadMoreProducts(page)
            }
            .setLoadingTriggerThreshold(4)
            .setCustomErrorItem(CustomErrorItem())
            .setCustomLoadingItem(CustomLoadingItem())
            .build()
        noPaginate.showError(false)
        noPaginate.setNoMoreItems(cursor.next == null)
    }

    private fun loadMoreProducts(page: Int) {
        noPaginate.showLoading(true)
        noPaginate.showError(false)
        viewModel.loadProducts(page).observe(viewLifecycleOwner) {
            it?.response?.let {
                noPaginate.showLoading(false)
                if (it.success) {
                    noPaginate.showError(false)
                    this.page = it.cursor?.current?.plus(1) ?: 1
                    noPaginate.setNoMoreItems(it.cursor?.next == null)
                    productsAdapter.addMoreProducts(it.data)
                } else {
                    noPaginate.showError(true)
                    this.page -= 1
                }
                requireContext().log("page $page, cursor ${it.cursor?.next}")
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (::noPaginate.isInitialized) noPaginate.unbind()
    }

    override fun onProductClicked(id: Int) {
        findNavController().navigate(
            R.id.actionShowProductDetails,
            bundleOf(getString(R.string.nav_key_product_id) to id)
        )
    }
}