package com.rishi.newsapp.ui.LanguagePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.Language
import com.rishi.newsapp.databinding.FragmentLanguageBinding
import com.rishi.newsapp.ui.HomeActivity
import com.rishi.newsapp.ui.HomePage.HomeFragment
import com.rishi.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setupUI() {
        binding.recyclerLanguage.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerLanguage.adapter = languageAdapter

        languageAdapter.addData(Constants.LANGUAGE_LIST)
        languageAdapter.notifyDataSetChanged()

        languageAdapter.itemClickListener ={ pos,list ->
            var article = list as Language
                val bundle = Bundle().apply {
                    putString("language", article.id)
                    putString("language_name", article.name)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
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
                R.color.black
            )
        (activity as HomeActivity).binding.txtSource.setTextColor(resources.getColor(R.color.black))
        (activity as HomeActivity).binding.imgLanguage.backgroundTintList =
            requireContext().resources.getColorStateList(
                R.color.red
            )
        (activity as HomeActivity).binding.txtLanguage.setTextColor(resources.getColor(R.color.red))

    }
}