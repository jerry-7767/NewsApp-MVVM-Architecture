package com.rishi.newsapp.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rishi.newsapp.MVVMApplication
import com.rishi.newsapp.R
import com.rishi.newsapp.databinding.ActivityHomeBinding
import com.rishi.newsapp.di.component.DaggerActivityComponent
import com.rishi.newsapp.di.module.ActivityModule
import com.rishi.newsapp.ui.CountryPage.CountryFragment
import com.rishi.newsapp.ui.HomePage.HomeFragment
import com.rishi.newsapp.ui.LanguagePage.LanguageFragment
import com.rishi.newsapp.ui.SearchPage.SearchFragment
import com.rishi.newsapp.ui.SourcePage.SourceFragment


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            displayView()
        }
        setupUI()
    }

    private fun setupUI() {
        binding.llHome.setOnClickListener {
            val fragmentHome =
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (fragmentHome !is HomeFragment) {
                val fragment: Fragment = HomeFragment()
                val transaction = this@HomeActivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        binding.llSearch.setOnClickListener {
            val fragmentHome =
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (fragmentHome !is SearchFragment) {
                val fragment: Fragment = SearchFragment()
                val transaction = this@HomeActivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        binding.llCountry.setOnClickListener {
            val fragmentHome =
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (fragmentHome !is CountryFragment) {
                val fragment: Fragment = CountryFragment()
                val transaction = this@HomeActivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        binding.llSource.setOnClickListener {
            val fragmentHome =
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (fragmentHome !is SourceFragment) {
                val fragment: Fragment = SourceFragment()
                val transaction = this@HomeActivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        binding.llLanguage.setOnClickListener {
            val fragmentHome =
                supportFragmentManager.findFragmentById(R.id.fragment_container_view)
            if (fragmentHome !is LanguageFragment) {
                val fragment: Fragment = LanguageFragment()
                val transaction = this@HomeActivity.supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container_view) is HomeFragment) {
            if (doubleBackToExitPressedOnce) {
                finish()
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
                doubleBackToExitPressedOnce = true
                Handler().postDelayed({
                    doubleBackToExitPressedOnce = false
                }, 2000)
            }
        } else {
            super.onBackPressed()
        }
    }
    private fun displayView() {
        var fragment: Fragment? = null
        when (0) {
            0 -> fragment = HomeFragment()
        }
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_container_view, fragment)
                .commit()
        } else {
        }
    }

    private fun injectDependencies() {
         DaggerActivityComponent.builder()
             .applicationComponent((application as MVVMApplication).applicationComponent)
             .activityModule(ActivityModule(this)).build().inject(this)
     }
}