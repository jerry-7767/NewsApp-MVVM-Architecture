package com.rishi.newsapp.ui.HomePage

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Html
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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.databinding.FragmentHomeBinding
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.ui.SourcePage.SourceFragment
import com.rishi.newsapp.ui.base.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.rishi.newsapp.data.model.Article
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var mainviewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    lateinit var countrylist: ArrayList<Country>
    lateinit var countrylist_name: ArrayList<String>

    @Inject
    lateinit var newsViewpagerAdapter: NewsViewpagerAdapter

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private var progressDialog: ProgressDialog? = null
    var pos = 0
    var language_name = ""
    var strCountry_id = ""
    var country_name = ""
    var clickSource = ""
    var strSource1 = ""
    var strSource2 = ""
    var strSource3 = ""
    var strSource4 = ""
    var strLanguage = ""
    var strSourceID = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        if (arguments != null) {
            pos = requireArguments().getInt("pos")
            strCountry_id = requireArguments().getString("country_id").toString()
            strLanguage = requireArguments().getString("language").toString()
            strSourceID = requireArguments().getString("source_id").toString()
            language_name = requireArguments().getString("language_name").toString()
            clickSource = requireArguments().getString("source_name").toString()
            country_name = requireArguments().getString("country_name").toString()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog!!.setCancelable(false)
        countrylist = ArrayList()
        countrylist_name = ArrayList()

        setupViewModel()
        setupUI()
        setupObserver()
        if (strLanguage.isNotEmpty() && !strLanguage.equals("null")) {
            mainviewModel.fetchNewsbyLanguage(strLanguage)
        }
        if (strSourceID.isNotEmpty() && !strSourceID.equals("null")) {
            mainviewModel.fetchNewsbySources(strSourceID)
        }
        if (strCountry_id.isNotEmpty() && !strCountry_id.equals("null")) {
            mainviewModel.fetchNewsbyCountry(strCountry_id)
        }
    }
    private fun setupViewModel() {
        mainviewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerNews
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerNews.adapter = newsAdapter

        binding.llSource1.setOnClickListener {
            mainviewModel.fetchNewsbySources(strSource1)
            clickSource = binding.txtSource1.text.toString()
        }

        binding.llSource2.setOnClickListener {
            mainviewModel.fetchNewsbySources(strSource2)
            clickSource = binding.txtSource2.text.toString()
        }

        binding.llSource3.setOnClickListener {
            mainviewModel.fetchNewsbySources(strSource3)
            clickSource = binding.txtSource3.text.toString()
        }

        binding.llSource4.setOnClickListener {
            mainviewModel.fetchNewsbySources(strSource4)
            clickSource = binding.txtSource4.text.toString()
        }

        binding.llMore.setOnClickListener {
            val activity = context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, SourceFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainviewModel.uistate_count.collect {
                    when (it) {
                        is UiState.Success -> {
                            val count = it.data.totalResults.toString() + " newest"
                            val text =
                                "<font color=#000000>There are </font> <font color=#D34946> $count </font>  " +
                                        "<font color=#000000> article\n for you from $country_name </font>"
                            binding.txtArticleCount.text = Html.fromHtml(text)
                            if (it.data.articles.isNotEmpty()) {
                                binding.llData.visibility = View.VISIBLE
                                binding.llNodata.visibility = View.GONE
                                renderSlider(it.data.articles)
                                binding.viewpagerNews.adapter = newsViewpagerAdapter
                                hideProgressDialog()
                            } else {
                                binding.llData.visibility = View.GONE
                                binding.llNodata.visibility = View.VISIBLE
                                hideProgressDialog()
                            }
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainviewModel.uistate_newslanguage_count.collect {
                    when (it) {
                        is UiState.Success -> {
                            val count = it.data.totalResults.toString() + " newest"
                            val text =
                                "<font color=#000000>There are </font> <font color=#D34946> $count </font>  " +
                                        "<font color=#000000> article\n for you from $language_name Language </font>"
                            binding.txtArticleCount.text = Html.fromHtml(text)
                            if (it.data.articles.isNotEmpty()) {
                                binding.llData.visibility = View.VISIBLE
                                binding.llNodata.visibility = View.GONE
                                renderSlider(it.data.articles)
                                binding.viewpagerNews.adapter = newsViewpagerAdapter
                                hideProgressDialog()
                            } else {
                                binding.llData.visibility = View.GONE
                                binding.llNodata.visibility = View.VISIBLE
                                hideProgressDialog()
                            }
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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainviewModel.uiState_newsbysources.collect {
                    when (it) {
                        is UiState.Success -> {
                            val count = it.data.totalResults.toString() + " newest"
                            val text =
                                "<font color=#000000>There are </font> <font color=#D34946> $count </font>  " +
                                        "<font color=#000000> article\n for you from $clickSource Source</font>"
                            binding.txtArticleCount.text = Html.fromHtml(text)
                            if (it.data.articles.isNotEmpty()) {
                                binding.llData.visibility = View.VISIBLE
                                binding.llNodata.visibility = View.GONE
                                renderSlider(it.data.articles)
                                binding.viewpagerNews.adapter = newsViewpagerAdapter
                                hideProgressDialog()
                            } else {
                                binding.llData.visibility = View.GONE
                                hideProgressDialog()
                                binding.llNodata.visibility = View.VISIBLE
                            }
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainviewModel.uistate_country_.collect {
                    when (it) {
                        is UiState.Success -> {
                            countrylist.addAll(it.data)
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainviewModel.uistate_sources_.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.txtSource1.text = it.data[0].name
                            binding.txtSource2.text = it.data[1].name
                            binding.txtSource3.text = it.data[2].name
                            binding.txtSource4.text = it.data[3].name

                            strSource1 = it.data[0].id
                            strSource2 = it.data[1].id
                            strSource3 = it.data[2].id
                            strSource4 = it.data[3].id

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

    private fun renderSlider(articles: List<Article>) {
        newsViewpagerAdapter.clear()
        newsAdapter.clear()
        newsViewpagerAdapter.addData(articles)
        newsViewpagerAdapter.notifyDataSetChanged()

        newsAdapter.addData(articles)
        newsAdapter.notifyDataSetChanged()
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

    override fun onResume() {
        super.onResume()
        (activity as HomeActivity).binding.imgHome.backgroundTintList =
            requireContext().resources.getColorStateList(R.color.red)
        (activity as HomeActivity).binding.txtHome.setTextColor(resources.getColor(R.color.red))
        (activity as HomeActivity).binding.imgSearch.backgroundTintList =
            requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtSearch.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgCountry.backgroundTintList =
            requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtCountry.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgSource.backgroundTintList =
            requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtSource.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgLanguage.backgroundTintList =
            requireContext().resources.getColorStateList(R.color.black)
        (activity as HomeActivity).binding.txtLanguage.setTextColor(resources.getColor(R.color.black))

    }
}