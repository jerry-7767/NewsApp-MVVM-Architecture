package com.rishi.newsapp.ui.LanguagePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.R
import com.rishi.newsapp.databinding.FragmentCountryBinding
import com.rishi.newsapp.databinding.FragmentLanguageBinding
import com.rishi.newsapp.di.component.DaggerLanguageFragmentComponent
import com.rishi.newsapp.di.module.LanguageFragmentModule
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.utils.Constants
import javax.inject.Inject

class LanguageFragment : Fragment() {
    private lateinit var binding: FragmentLanguageBinding

    @Inject
    lateinit var languageAdapter: LanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    private fun injectDependencies() {
        DaggerLanguageFragmentComponent.builder().languageFragmentModule(LanguageFragmentModule(this))
            .applicationComponent((requireContext().applicationContext as MVVMApplication).applicationComponent)
            .build()
            .inject(this)
    }

    private fun setupUI() {
        binding.recyclerLanguage.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerLanguage.adapter = languageAdapter

        languageAdapter.addData(Constants.LANGUAGE_LIST)
        languageAdapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }
    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).binding.imgHome.backgroundTintList = requireContext().resources.getColorStateList(
            R.color.black)
        (activity as HomeActivity).binding.txtHome.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgSearch.backgroundTintList = requireContext().resources.getColorStateList(
            R.color.black)
        (activity as HomeActivity).binding.txtSearch.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgCountry.backgroundTintList = requireContext().resources.getColorStateList(
            R.color.black)
        (activity as HomeActivity).binding.txtCountry.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgSource.backgroundTintList = requireContext().resources.getColorStateList(
            R.color.black)
        (activity as HomeActivity).binding.txtSource.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgLanguage.backgroundTintList = requireContext().resources.getColorStateList(
            R.color.red)
        (activity as HomeActivity).binding.txtLanguage.setTextColor(resources.getColor(R.color.red))

    }
}