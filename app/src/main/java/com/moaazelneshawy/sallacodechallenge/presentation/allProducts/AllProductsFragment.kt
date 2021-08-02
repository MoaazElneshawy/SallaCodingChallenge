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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBrandProducts(page)
    }

    private fun getBrandProducts(page: Int) {
        viewModel.loadProducts(page).observe(viewLifecycleOwner) {
            it?.response?.let {

                when (page) {
                    1 -> {
                        binding.brand = it.brand
                        productsAdapter.fill(it.data)
                    }
                    else -> productsAdapter.addMoreProducts(it.data)
                }
                binding.rvProducts.apply {
                    layoutManager =
                        GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
                    this.adapter = productsAdapter
                }
                this.page += 1
                it.cursor?.let { cursor ->
                    if (::noPaginate.isInitialized.not())
                        setupPagination(cursor)
                    noPaginate.setNoMoreItems(cursor.next == null)
                }
            }
        }
    }

    private fun setupPagination(cursor: Cursor) {
        noPaginate = NoPaginate.with(binding.rvProducts)
            .setOnLoadMoreListener {
//                getBrandProducts(page)
                requireContext().log("page $page, cursor ${cursor.next}")
            }
            .setLoadingTriggerThreshold(2)
            .setCustomErrorItem(CustomErrorItem())
            .setCustomLoadingItem(CustomLoadingItem())
            .build()
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