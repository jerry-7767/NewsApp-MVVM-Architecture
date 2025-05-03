package com.rishi.newsapp.ui.HomePage

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.rishi.newsapp.databinding.NewsSingleItemBinding
import me.amitshekhar.newsapp.data.model.Article

class NewsAdapter(
    private val articleList: ArrayList<Article>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val binding: NewsSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.txtDesc.text = article.title
            binding.txtSource.text = article.source.name
            Glide.with(binding.imgNews.context)
                .load(article.imageUrl)
                .into(binding.imgNews)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsSingleItemBinding.inflate(
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
        holder.bind(articleList[position])
    }

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}