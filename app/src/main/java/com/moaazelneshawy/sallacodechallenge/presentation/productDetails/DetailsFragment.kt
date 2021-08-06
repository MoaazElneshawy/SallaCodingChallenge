package com.moaazelneshawy.sallacodechallenge.presentation.productDetails

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.sallacodechallenge.R
import com.moaazelneshawy.sallacodechallenge.data.utils.CirclePagerIndicatorDecoration
import com.moaazelneshawy.sallacodechallenge.data.utils.applyCustomShape
import com.moaazelneshawy.sallacodechallenge.databinding.FragmentDetailsBinding
import com.moaazelneshawy.sallacodechallenge.presentation.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel by viewModels<ProductsViewModel>()
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addShapeToViews()
        arguments?.let {
            val productId = it.getInt(getString(R.string.nav_key_product_id))
            getProductDetails(productId)
        }
    }

    private fun addShapeToViews() {
        binding.ibFav.applyCustomShape(
            backgroundColor = Color.WHITE,
            shapeType = GradientDrawable.OVAL,
            paddingBottom = 5,
            paddingTop = 5,
            paddingLeft = 5,
            paddingRight = 5
        )
        binding.ibShare.applyCustomShape(
            backgroundColor = Color.WHITE,
            shapeType = GradientDrawable.OVAL,
            paddingBottom = 5,
            paddingTop = 5,
            paddingLeft = 5,
            paddingRight = 5
        )
        binding.clDetails.applyCustomShape(
            shapeType = GradientDrawable.RECTANGLE,
            backgroundColor = Color.WHITE,
            topLeftRadius = 30f,
            topRightRadius = 30f,
            paddingRight = 30,
            paddingLeft = 30,
            paddingTop = 80,
            paddingBottom = 30
        )
    }

    private fun getProductDetails(productId: Int) {
        viewModel.loadProductDetails(productId).observe(viewLifecycleOwner) {
            it.response?.data?.let {
                sliderAdapter = SliderAdapter(it)
                with(binding) {
                    this.product = it
                    rvSlider.apply {
                        layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                        adapter = sliderAdapter
                    }
                    PagerSnapHelper().attachToRecyclerView(rvSlider)
                    rvSlider.addItemDecoration(CirclePagerIndicatorDecoration())
                }
            }
        }
    }

}