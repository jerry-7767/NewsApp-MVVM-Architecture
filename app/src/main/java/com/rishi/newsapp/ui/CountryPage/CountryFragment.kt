package com.rishi.newsapp.ui.CountryPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.R
import com.rishi.newsapp.databinding.FragmentCountryBinding
import com.rishi.newsapp.databinding.FragmentHomeBinding
import com.rishi.newsapp.di.component.DaggerCountryFragmentComponent
import com.rishi.newsapp.di.module.CountryFragmentModule
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.utils.Constants
import javax.inject.Inject

class CountryFragment : Fragment() {

    private lateinit var binding: FragmentCountryBinding
    @Inject
    lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectdependencies()
        super.onCreate(savedInstanceState)
    }

    private fun injectdependencies() {
        DaggerCountryFragmentComponent.builder().countryFragmentModule(CountryFragmentModule(this))
            .applicationComponent((requireContext().applicationContext as MVVMApplication).applicationComponent)
            .build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.recyclerCountry.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCountry.adapter = countryAdapter

        countryAdapter.addData(Constants.COUNTRY_LIST)
        countryAdapter.notifyDataSetChanged()


    }


    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).binding.imgHome.backgroundTintList = requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtHome.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgSearch.backgroundTintList = requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtSearch.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgCountry.backgroundTintList = requireContext().resources.getColorStateList(R.color.red)
        (activity as HomeActivity).binding.txtCountry.setTextColor(resources.getColor(R.color.red))
        (activity as HomeActivity).binding.imgSource.backgroundTintList = requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtSource.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgLanguage.backgroundTintList = requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtLanguage.setTextColor(resources.getColor(R.color.black))
    }
}