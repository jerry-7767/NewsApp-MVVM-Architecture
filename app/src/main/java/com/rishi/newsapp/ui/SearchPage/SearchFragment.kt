package com.rishi.newsapp.ui.SearchPage

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.R
import com.rishi.newsapp.databinding.FragmentSearchBinding
import com.rishi.newsapp.di.component.DaggerSearchFragmentComponent
import com.rishi.newsapp.di.module.SearchFragmentModule
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.ui.HomePage.HomeViewModel
import com.rishi.newsapp.ui.HomePage.NewsAdapter
import com.rishi.newsapp.ui.base.UiState
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    var searchQuery = ""

    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var searchViewModel: SearchViewModel

    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectd()
        super.onCreate(savedInstanceState)
    }

    private fun injectd() {
        DaggerSearchFragmentComponent.builder().searchFragmentModule(SearchFragmentModule(this))
            .applicationComponent((requireContext().applicationContext as MVVMApplication).applicationComponent)
            .build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog!!.setCancelable(false)
        setupObserver()
        setupUI()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerSearch
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerSearch.adapter = newsAdapter

        binding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchQuery = query
                    // viewModel.fetchTopHeadlinesBySearch(AppConstants.EXTRAS_COUNTRY,query)
                }
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                searchQuery = query
                if (query.isNotEmpty()) {
                    newsAdapter.notifyDataSetChanged();
                    searchViewModel.fetchNewsbysearch(query)
                }
                return false
            }
        })
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
                R.color.red
            )
        (activity as HomeActivity).binding.txtSearch.setTextColor(resources.getColor(R.color.red))
        (activity as HomeActivity).binding.imgCountry.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.black
            )
        (activity as HomeActivity).binding.txtCountry.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgSource.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.black
            )
        (activity as HomeActivity).binding.txtSource.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgLanguage.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.black
            )
        (activity as HomeActivity).binding.txtLanguage.setTextColor(resources.getColor(R.color.black))

    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uistate_search_news.collect {
                    when (it) {
                        is UiState.Success -> {
                            if (it.data.articles.isNotEmpty()) {
                                binding.recyclerSearch.visibility = View.VISIBLE
                                binding.llNodata.visibility = View.GONE
                                newsAdapter.clear()

                                newsAdapter.addData(it.data.articles)
                                newsAdapter.notifyDataSetChanged()
                                hideProgressDialog()
                            } else {
                                binding.recyclerSearch.visibility = View.GONE
                                binding.llNodata.visibility = View.VISIBLE
                                hideProgressDialog()
                            }
                        }

                        is UiState.Error -> {
                            hideProgressDialog()
                        }

                        is UiState.Loading -> {
//                            showProgressDialog()
                        }
                    }
                }
            }
        }
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