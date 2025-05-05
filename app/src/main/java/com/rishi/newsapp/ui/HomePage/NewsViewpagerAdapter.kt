package com.rishi.newsapp.ui.HomePage

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rishi.newsapp.databinding.ViewpagerSingleItemBinding
import com.rishi.newsapp.data.model.Article
import kotlin.collections.ArrayList

class NewsViewpagerAdapter(
    private var articleList: ArrayList<Article>
) : RecyclerView.Adapter<NewsViewpagerAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ViewpagerSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            val aUrl: String = article.imageUrl
            binding.txtDesc.text = article.description
            binding.txtSource.text = article.source.name
            if (aUrl != null) {
                Glide.with(binding.imgnews.context)
                    .load(aUrl)
                    .into(binding.imgnews)
            }
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ViewpagerSingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}

