package news.com.newsapp.home.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import news.com.newsapp.databinding.ItemNewsArticleBinding
import news.com.newsapp.home.model.Article
import news.com.newsapp.home.view.MainActivity
import news.com.newsapp.utils.Utility
import java.io.File


/**
 * Created by Tarak on 25,April,2018
 *
 */
class AllFeedsListAdapter(val list: List<Article>?, private val activity: MainActivity) : RecyclerView.Adapter<AllFeedsListAdapter.ViewHolder>() {

    var articleLiveDataList = MutableLiveData<Article>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent!!.context)
        val itemBinding = ItemNewsArticleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = list!![position]

        holder!!.binding.article = item

        holder.binding.imgShare.setOnClickListener {

            val bitmap = Utility.takeScreenShot(holder.binding.cardViewArticle)

            val filePath = Utility.store(bitmap, activity)

            Utility.shareImage(File(filePath), item.url, activity)
        }


        holder.itemView.setOnClickListener {

            articleLiveDataList.postValue(list[position])

        }

    }


    inner class ViewHolder(internal val binding: ItemNewsArticleBinding) : RecyclerView.ViewHolder(binding.root)
}