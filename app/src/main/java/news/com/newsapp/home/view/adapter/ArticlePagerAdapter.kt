package com.mep.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import news.com.newsapp.R
import news.com.newsapp.databinding.ItemPagerArticleBinding
import news.com.newsapp.home.model.Article
import news.com.newsapp.home.view.adapter.custom.RecyclingPagerAdapter
import news.com.newsapp.utils.TimeManager
import news.com.newsapp.utils.Utility
import java.io.File


/**
 * Created by Tarak on 19,March,2018
 *
 */
class ArticlePagerAdapter(private val context: Context, val articleList: List<Article>) : RecyclingPagerAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, container: ViewGroup): View {

        val inflater = LayoutInflater.from(context)
        val binding: ItemPagerArticleBinding?

        binding = ItemPagerArticleBinding.inflate(inflater!!)
        val article = articleList[position]
        binding.article = article
//        binding.txtPublishedAt.text = TimeManager.formatDate(article.publishedAt)


        binding.imgShare.setOnClickListener {
            binding.imgShare.visibility = View.GONE
            val bitmap = Utility.takeScreenShot(binding.cardViewArticle)
            val filePath = Utility.store(bitmap, context)
            binding.imgShare.visibility = View.VISIBLE
            Utility.shareImage(File(filePath), article.url, context)
        }

        binding.root.setOnClickListener {
            val customTabsIntent = CustomTabsIntent.Builder()
                    .addDefaultShareMenuItem()
                    .setToolbarColor(context.resources.getColor(R.color.colorPrimary))
                    .build()
            customTabsIntent.launchUrl(context, Uri.parse(articleList[position].url))
        }

        return binding.root
    }

    override fun getCount(): Int {
        return if (articleList.size > 5) 5 else articleList.size
    }
}