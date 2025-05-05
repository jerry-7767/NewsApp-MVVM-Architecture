package com.rishi.newsapp.ui.LanguagePage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.data.model.Language
import com.rishi.newsapp.databinding.RecyclerItemListBinding
import com.rishi.newsapp.ui.HomePage.HomeFragment
import com.rishi.newsapp.utils.ItemClickListener
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LanguageAdapter @Inject constructor(
    private val articleList: ArrayList<Language>
) : RecyclerView.Adapter<LanguageAdapter.NewsViewHolder>() {
    lateinit var itemClickListener: ItemClickListener<Any>
    class NewsViewHolder(private val binding: RecyclerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Language,itemClickListener: ItemClickListener<Any>) {
            binding.txtDesc.text = article.name
            itemView.setOnClickListener {
                itemClickListener(bindingAdapterPosition,article)
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
        holder.bind(articleList[position],itemClickListener)
    }

    fun addData(list: List<Language>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}