package com.rishi.newsapp.ui.SourcePage

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
import me.amitshekhar.newsapp.data.model.SourcesList

class SourceAdapter(
    private val articleList: ArrayList<SourcesList>,
   val context: Context
) : RecyclerView.Adapter<SourceAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val binding: RecyclerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: SourcesList, context: Context) {
            binding.txtDesc.text = article.name
            itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("source_id", article.id)
                    putString("source_name", article.name)
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

    fun addData(list: List<SourcesList>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}