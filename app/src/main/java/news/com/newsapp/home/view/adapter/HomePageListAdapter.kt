package news.com.newsapp.home.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mep.ui.adapters.ArticlePagerAdapter
import news.com.newsapp.databinding.ItemHomeFragmentListBinding
import news.com.newsapp.home.model.ArticleList
import news.com.newsapp.home.view.MainActivity


/**
 * Created by Tarak on 25,April,2018
 *
 */
class HomePageListAdapter(private val activity: MainActivity, val list: MutableList<ArticleList>) : RecyclerView.Adapter<HomePageListAdapter.ViewHolder>() {

    var articleLiveDataList = MutableLiveData<ArticleList>()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent!!.context)
        val itemBinding = ItemHomeFragmentListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val item = list[position]

        holder!!.getBinding().item = item

        holder.getBinding().viewPager.setPadding(80, 0, 80, 0)
        holder.getBinding().viewPager.clipToPadding = false
        holder.getBinding().viewPager.pageMargin = 30
        holder.getBinding().viewPager.adapter = ArticlePagerAdapter(activity, list[position].articleList)

        holder.getBinding().viewPager.setPageTransformer(false, object : ViewPager.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                if (holder.getBinding().viewPager.currentItem == 0) {
                    page.translationX = -50f
                } else if (holder.getBinding().viewPager.currentItem == holder.getBinding().viewPager.adapter.count - 1) {
                    page.translationX = 50f
                } else {
                    page.translationX = 0f
                }
            }
        })

        holder!!.getBinding().txtMore.setOnClickListener({

            articleLiveDataList.postValue(list[position])

        })
    }

    inner class ViewHolder(private val binding: ItemHomeFragmentListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun getBinding(): ItemHomeFragmentListBinding {
            return binding
        }
    }

}