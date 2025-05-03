package com.rishi.newsapp.ui.SourcePage

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.R
import com.rishi.newsapp.databinding.FragmentCountryBinding
import com.rishi.newsapp.databinding.FragmentSourceBinding
import com.rishi.newsapp.di.component.DaggerLanguageFragmentComponent
import com.rishi.newsapp.di.component.DaggerSourceFragmentComponent
import com.rishi.newsapp.di.module.LanguageFragmentModule
import com.rishi.newsapp.di.module.SourceFragmentModule
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.ui.base.UiState
import com.rishi.newsapp.utils.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject

class SourceFragment : Fragment() {
    private lateinit var binding: FragmentSourceBinding

    @Inject
    lateinit var sourceAdapter: SourceAdapter

    @Inject
    lateinit var sourceViewModel: SourceViewModel

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSourceBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog!!.setCancelable(false)
        sourceViewModel.fetchSource()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                sourceViewModel.uistate_source.collect{
                    when(it){
                        is UiState.Success ->{
                            binding.recyclerSource.layoutManager = LinearLayoutManager(requireContext())
                            binding.recyclerSource.adapter = sourceAdapter

                            sourceAdapter.addData(it.data)
                            sourceAdapter.notifyDataSetChanged()
                            hideProgressDialog()
                        }
                        is UiState.Error -> {
                            hideProgressDialog()
                        }

                        is UiState.Loading -> {
                            showProgressDialog()
                        }
                    }
                }
            }
        }
    }

    private fun injectDependencies() {
        DaggerSourceFragmentComponent.builder().sourceFragmentModule(SourceFragmentModule(this))
            .applicationComponent((requireContext().applicationContext as MVVMApplication).applicationComponent)
            .build()
            .inject(this)
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
            R.color.red)
        (activity as HomeActivity).binding.txtSource.setTextColor(resources.getColor(R.color.red))
        (activity as HomeActivity).binding.imgLanguage.backgroundTintList = requireContext().resources.getColorStateList(
            R.color.black)
        (activity as HomeActivity).binding.txtLanguage.setTextColor(resources.getColor(R.color.black))

    }
    private fun showProgressDialog() {
        if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
            progressDialog!!.setContentView(R.layout.item_loader)
            Objects.requireNonNull<Window>(progressDialog!!.window)
                .setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun hideProgressDialog() {
        if (progressDialog!!.isShowing) progressDialog!!.dismiss()
    }
}