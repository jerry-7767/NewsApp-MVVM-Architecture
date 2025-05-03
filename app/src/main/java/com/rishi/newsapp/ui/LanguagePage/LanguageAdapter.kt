package com.rishi.newsapp.ui.LanguagePage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.Language
import com.rishi.newsapp.databinding.RecyclerItemListBinding
import com.rishi.newsapp.ui.HomePage.HomeFragment

class LanguageAdapter(
    private val articleList: ArrayList<Language>,
   val context: Context
) : RecyclerView.Adapter<LanguageAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val binding: RecyclerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Language, context: Context) {
            binding.txtDesc.text = article.name
            itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("language", article.id)
                    putString("language_name", article.name)
                }

                val fragment = HomeFragment().apply {
                    arguments = bundle
                }

                val activity = context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            RecyclerItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articleList[position],context)
    }

    fun addData(list: List<Language>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}