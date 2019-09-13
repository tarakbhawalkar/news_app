package news.com.newsapp.home.view

import android.arch.lifecycle.Observer
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ethanhua.skeleton.Skeleton
import kotlinx.android.synthetic.main.activity_main.*
import news.com.newsapp.R
import news.com.newsapp.base.BaseFragment
import news.com.newsapp.base.Constants
import news.com.newsapp.databinding.FragmentAllFeedsBinding
import news.com.newsapp.home.model.Article
import news.com.newsapp.home.view.adapter.AllFeedsListAdapter
import news.com.newsapp.home.viewmodel.ArticleListingViewModel


/**
 * Created by Tarak on 25,April,2018
 *
 */
class AllFeedsFragment : BaseFragment() {


    var binding: FragmentAllFeedsBinding? = null
    var article: Article? = null
    var articleList = mutableListOf<Article>()

    companion object {
        fun newInstance(category: String?, topHeadlines: Boolean,
                        query: String?): AllFeedsFragment {

            val allFeedsFragment = AllFeedsFragment()
            val args = Bundle()
            args.putString(Constants.CATEGORY, category)
            args.putBoolean(Constants.TOP_HEADLINES, topHeadlines)
            args.putString(Constants.QUERY, query)
            allFeedsFragment.arguments = args
            return allFeedsFragment

        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentAllFeedsBinding.inflate(inflater!!, container, false)
        binding!!.viewModel = ArticleListingViewModel()

        initData()

        (activity as MainActivity).menu.findItem(R.id.search).isVisible = false
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        return binding!!.root

    }

    private fun initData() {

        val adapter = AllFeedsListAdapter(articleList, activity as MainActivity)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding!!.recyclerAllFeeds.layoutManager = layoutManager
        binding!!.recyclerAllFeeds.adapter = adapter


//        val skeleton = Skeleton.bind(binding!!.recyclerAllFeeds)
//                .load(R.layout.item_news_article)
//                .show()


        var category: String? = arguments!!.getString(Constants.CATEGORY)
        if (category.equals(getString(R.string.top_headlines))) {
            category = null
            (activity as MainActivity).binding.toolbar.title = getString(R.string.top_headlines)
        } else {
            (activity as MainActivity).binding.toolbar.title = category
        }

//        (activity as MainActivity).showProgress()
        binding!!.progressBar.visibility = View.VISIBLE
        if (arguments!!.getBoolean(Constants.TOP_HEADLINES)) {

            binding!!.viewModel!!.getAllHeadlines(category)


        } else {

            (activity as MainActivity).binding.toolbar.title = arguments!!.getString(Constants.QUERY)
            binding!!.viewModel!!.getCustomFeeds(arguments!!.getString(Constants.QUERY))
        }

        binding!!.viewModel!!.customNews.observe(activity as MainActivity,
                Observer<MutableList<Article>> { t ->
                    t!!.filter { it ->
                        !TextUtils.isEmpty(it.urlToImage) && !TextUtils.isEmpty(it.title)
                                && !TextUtils.isEmpty(it.description.trim())
                    }
                    articleList.clear()
                    articleList.addAll(t)
                    //                        skeleton.hide()
                    //                        (activity as MainActivity).hideProgress()
                    binding!!.progressBar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                })


        adapter.articleLiveDataList.observe((activity as MainActivity), object : Observer<Article> {
            override fun onChanged(t: Article?) {

                val customTabsIntent = CustomTabsIntent.Builder()
                        .addDefaultShareMenuItem()
                        .setToolbarColor(activity.resources.getColor(R.color.colorPrimary))
                        .setStartAnimations(activity, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                        .setExitAnimations(activity, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .build()
                customTabsIntent.launchUrl(activity, Uri.parse(t!!.url))
            }
        })


        binding!!.viewModel!!.error.observe(activity as MainActivity,
                Observer<String> { error ->
                    Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
                    binding!!.progressBar.visibility = View.GONE

                })

    }
}