package com.rishi.newsapp.ui.CountryPage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.databinding.RecyclerItemListBinding
import com.rishi.newsapp.ui.HomePage.HomeFragment

class CountryAdapter(
    private val articleList: ArrayList<Country>,
    val context: Context
) : RecyclerView.Adapter<CountryAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val binding: RecyclerItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Country, context: Context) {
            binding.txtDesc.text = article.name
            itemView.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("country_id", article.id)
                    putString("country_name", article.name)
                    putInt("pos", bindingAdapterPosition)
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

    fun addData(list: List<Country>) {
        articleList.addAll(list)
    }

    fun clear() {
        articleList.clear()
    }
}