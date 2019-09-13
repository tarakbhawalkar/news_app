package news.com.newsapp.home.view


import android.os.Bundle
import android.app.Fragment
import android.arch.lifecycle.Observer
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import news.com.newsapp.R

import news.com.newsapp.base.BaseFragment
import news.com.newsapp.databinding.FragmentHomeBinding
import news.com.newsapp.home.model.Article
import news.com.newsapp.home.model.ArticleList
import news.com.newsapp.home.view.adapter.HomePageListAdapter
import news.com.newsapp.home.viewmodel.ArticleListingViewModel
import news.com.newsapp.utils.FragmentHelper
import android.support.customtabs.CustomTabsIntent
import android.text.TextUtils
import android.widget.Toast
import org.w3c.dom.Text


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance(): HomeFragment {

            val homeFragment = HomeFragment()
            val args = Bundle()
            homeFragment.arguments = args
            return homeFragment

        }

    }

    var binding: FragmentHomeBinding? = null
    var adapter: HomePageListAdapter? = null
    var homeArticleList = mutableListOf<ArticleList>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater!!, container, false)

        (activity as MainActivity).binding.toolbar.title = getString(R.string.app_name)
        initData()
        return binding!!.root

    }

    private fun initData() {
        adapter = HomePageListAdapter(activity as MainActivity, homeArticleList)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding!!.recyclerHome.layoutManager = layoutManager
        binding!!.recyclerHome.adapter = adapter

        binding!!.viewModel = ArticleListingViewModel()


/*        val skeletonScreen = Skeleton.bind(binding!!.recyclerHome)
                .adapter(adapter)
                .count(5)
                .shimmer(true)
                .load(R.layout.item_home_fragment_list)
                .show();*/


//        (activity as MainActivity).showProgress()
        binding!!.progressBar.visibility = View.VISIBLE
        binding!!.viewModel!!.getAllHeadlines(null)

        binding!!.viewModel!!.allHeadLines.observe(activity as MainActivity, object : Observer<MutableList<Article>> {
            override fun onChanged(t: MutableList<Article>?) {
                t!!.filter { it ->
                    !TextUtils.isEmpty(it.urlToImage) && !TextUtils.isEmpty(it.title)
                            && !TextUtils.isEmpty(it.description)
                }
                homeArticleList.clear()
                homeArticleList.add(ArticleList(getString(R.string.top_headlines), t!!))
                binding!!.viewModel!!.getTechnologyNews("technology")
            }
        })
        binding!!.viewModel!!.technologyNews.observe(activity as MainActivity, object : Observer<MutableList<Article>> {
            override fun onChanged(t: MutableList<Article>?) {
                t!!.filter { it ->
                    !TextUtils.isEmpty(it.urlToImage) && !TextUtils.isEmpty(it.title)
                            && !TextUtils.isEmpty(it.description)
                }
                homeArticleList.add(ArticleList(getString(R.string.technology), t!!))
                binding!!.viewModel!!.getBusinessNews("business")
            }
        })
        binding!!.viewModel!!.businessNews.observe(activity as MainActivity, object : Observer<MutableList<Article>> {
            override fun onChanged(t: MutableList<Article>?) {

                t!!.filter { it ->
                    !TextUtils.isEmpty(it.urlToImage) && !TextUtils.isEmpty(it.title)
                            && !TextUtils.isEmpty(it.description)
                }
                homeArticleList.add(ArticleList(getString(R.string.business), t!!))
                binding!!.viewModel!!.getEntertainmentNews("entertainment")
            }
        })
        binding!!.viewModel!!.entertainmentNews.observe(activity as MainActivity, object : Observer<MutableList<Article>> {
            override fun onChanged(t: MutableList<Article>?) {
                t!!.filter { it ->
                    !TextUtils.isEmpty(it.urlToImage) && !TextUtils.isEmpty(it.title)
                            && !TextUtils.isEmpty(it.description)
                }
                homeArticleList.add(ArticleList(getString(R.string.entertainment), t!!))
                binding!!.viewModel!!.getSportsNews("sports")
            }
        })
        binding!!.viewModel!!.sportsNews.observe(activity as MainActivity, object : Observer<MutableList<Article>> {
            override fun onChanged(t: MutableList<Article>?) {

                t!!.filter { it ->
                    !TextUtils.isEmpty(it.urlToImage) && !TextUtils.isEmpty(it.title)
                            && !TextUtils.isEmpty(it.description)
                }

                homeArticleList.add(ArticleList(getString(R.string.sports), t))
//                (activity as MainActivity).hideProgress()
//                skeletonScreen.hide()
                binding!!.progressBar.visibility = View.GONE
                adapter!!.notifyDataSetChanged()
                (activity as MainActivity).menu.findItem(R.id.search).isVisible = true
                (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                (activity as MainActivity).supportActionBar!!.setDisplayShowHomeEnabled(false)
            }
        })


        binding!!.viewModel!!.error.observe(activity as MainActivity,
                Observer<String> { error ->
                    Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
                    binding!!.progressBar.visibility = View.GONE

                })

        adapter!!.articleLiveDataList.observe((activity as MainActivity), object : Observer<ArticleList> {
            override fun onChanged(t: ArticleList?) {

                FragmentHelper.replaceFragment(activity, R.id.home_container,
                        AllFeedsFragment.newInstance(t!!.category, true, null), true)

            }
        })

    }

}