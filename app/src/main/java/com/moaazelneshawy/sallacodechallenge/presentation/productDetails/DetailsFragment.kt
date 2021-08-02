package com.moaazelneshawy.sallacodechallenge.presentation.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.moaazelneshawy.sallacodechallenge.R
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
        arguments?.let {
            val productId = it.getInt(getString(R.string.nav_key_product_id))
            getProductDetails(productId)
        }
    }

    private fun getProductDetails(productId: Int) {
        viewModel.loadProductDetails(productId).observe(viewLifecycleOwner) {
            it.response?.data?.let {
                sliderAdapter = SliderAdapter(it)
                with(binding) {
                    this.product = it
                    vpSlider.apply {
                        orientation = ViewPager2.ORIENTATION_HORIZONTAL
                        adapter = sliderAdapter
                    }
                    binding.dotsIndicator.setViewPager2(binding.vpSlider)
                }

            }
        }
    }


}