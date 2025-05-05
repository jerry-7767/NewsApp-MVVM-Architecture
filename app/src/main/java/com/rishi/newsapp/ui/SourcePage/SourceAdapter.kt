package com.rishi.newsapp.ui.SourcePage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rishi.newsapp.databinding.RecyclerItemListBinding
import com.rishi.newsapp.utils.ItemClickListener
import dagger.hilt.android.qualifiers.ApplicationContext
import com.rishi.newsapp.data.model.SourcesList
import javax.inject.Inject

class SourceAdapter @Inject constructor(
    private val articleList: ArrayList<SourcesList>
) : RecyclerView.Adapter<SourceAdapter.NewsViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<Any>

    class NewsViewHolder(private val binding: RecyclerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: SourcesList, itemClickListener: ItemClickListener<Any>) {
            binding.txtDesc.text = article.name
            itemView.setOnClickListener {
                itemClickListener(bindingAdapterPosition, article)
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
        holder.bind(articleList[position], itemClickListener)
    }

    fun addData(list: List<SourcesList>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}