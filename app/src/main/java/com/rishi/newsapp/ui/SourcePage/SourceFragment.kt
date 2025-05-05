package com.rishi.newsapp.ui.SourcePage

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rishi.newsapp.R
import com.rishi.newsapp.databinding.FragmentSourceBinding
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.ui.HomePage.HomeFragment
import com.rishi.newsapp.ui.base.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.rishi.newsapp.data.model.Source
import com.rishi.newsapp.data.model.SourcesList
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class SourceFragment : Fragment() {
    private lateinit var binding: FragmentSourceBinding

    @Inject
    lateinit var sourceAdapter: SourceAdapter

    lateinit var sourceViewModel: SourceViewModel

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog!!.setCancelable(false)
        setupViewModel()
        sourceViewModel.fetchSource()
        setupObserver()
        setupUI()
    }

    private fun setupUI() {
        sourceAdapter.itemClickListener = { pos,list ->
            val article = list as SourcesList
            val bundle = Bundle().apply {
                putString("source_id", article.id)
                putString("source_name", article.name)
            }

            val fragment = HomeFragment().apply {
                arguments = bundle
            }

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupViewModel() {
        sourceViewModel = ViewModelProvider(this)[SourceViewModel::class.java]
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                sourceViewModel.uistate_source.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.recyclerSource.layoutManager =
                                LinearLayoutManager(requireContext())
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

    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).binding.imgHome.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.black
            )
        (activity as HomeActivity).binding.txtHome.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgSearch.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.black
            )
        (activity as HomeActivity).binding.txtSearch.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgCountry.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.black
            )
        (activity as HomeActivity).binding.txtCountry.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgSource.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.red
            )
        (activity as HomeActivity).binding.txtSource.setTextColor(resources.getColor(R.color.red))
        (activity as HomeActivity).binding.imgLanguage.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.black
            )
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