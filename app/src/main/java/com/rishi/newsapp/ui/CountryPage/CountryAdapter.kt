package com.rishi.newsapp.ui.CountryPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.databinding.RecyclerItemListBinding
import com.rishi.newsapp.utils.ItemClickListener

class CountryAdapter (
    private val articleList: ArrayList<Country>
) : RecyclerView.Adapter<CountryAdapter.NewsViewHolder>() {

    lateinit var itemClickListener: ItemClickListener<Any>

    class NewsViewHolder(private val binding: RecyclerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Country, itemClickListener: ItemClickListener<Any>) {
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

    fun addData(list: List<Country>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}