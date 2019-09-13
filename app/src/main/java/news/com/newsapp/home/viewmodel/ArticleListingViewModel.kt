package news.com.newsapp.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import news.com.newsapp.base.BaseViewModel
import news.com.newsapp.home.model.Article
import news.com.newsapp.retrofit.*

/**
 * Created by Tarak on 23,April,2018
 *
 */

class ArticleListingViewModel : BaseViewModel() {

    lateinit var request: Request

    var allHeadLines = MutableLiveData<MutableList<Article>>()
    var technologyNews = MutableLiveData<MutableList<Article>>()
    var businessNews = MutableLiveData<MutableList<Article>>()
    var entertainmentNews = MutableLiveData<MutableList<Article>>()
    var sportsNews = MutableLiveData<MutableList<Article>>()
    var customNews = MutableLiveData<MutableList<Article>>()
    var error = MutableLiveData<String>()


    fun getAllHeadlines(category: String?) {

        val call = RetrofitClient.getApiInterface().getNewsFeeds(category)

        request = RetrofitRequest(call, object : ResponseCallback<Response<MutableList<Article>>> {

            override fun onSuccess(response: Response<MutableList<Article>>) {

                allHeadLines.value = response.articles
                customNews.value = response.articles
            }

            override fun onError(errorDescription: String) {

                error.postValue(errorDescription)
            }

            override fun onFailure(throwable: Throwable) {
                error.postValue(throwable.message)
            }
        })

        request.enqueue()

    }

    fun getTechnologyNews(category: String?) {

        val call = RetrofitClient.getApiInterface().getNewsFeeds(category)

        request = RetrofitRequest(call, object : ResponseCallback<Response<MutableList<Article>>> {

            override fun onSuccess(response: Response<MutableList<Article>>) {

                technologyNews.value = response.articles
            }

            override fun onError(errorDescription: String) {

            }

            override fun onFailure(throwable: Throwable) {

            }
        })

        request.enqueue()

    }

    fun getBusinessNews(category: String?) {

        val call = RetrofitClient.getApiInterface().getNewsFeeds(category)

        request = RetrofitRequest(call, object : ResponseCallback<Response<MutableList<Article>>> {

            override fun onSuccess(response: Response<MutableList<Article>>) {

                businessNews.value = response.articles
            }

            override fun onError(errorDescription: String) {

            }

            override fun onFailure(throwable: Throwable) {

            }
        })

        request.enqueue()

    }

    fun getEntertainmentNews(category: String?) {

        val call = RetrofitClient.getApiInterface().getNewsFeeds(category)

        request = RetrofitRequest(call, object : ResponseCallback<Response<MutableList<Article>>> {

            override fun onSuccess(response: Response<MutableList<Article>>) {

                entertainmentNews.value = response.articles
            }

            override fun onError(errorDescription: String) {

            }

            override fun onFailure(throwable: Throwable) {

            }
        })

        request.enqueue()

    }

    fun getSportsNews(category: String?) {

        val call = RetrofitClient.getApiInterface().getNewsFeeds(category)

        request = RetrofitRequest(call, object : ResponseCallback<Response<MutableList<Article>>> {

            override fun onSuccess(response: Response<MutableList<Article>>) {

                sportsNews.value = response.articles
            }

            override fun onError(errorDescription: String) {

            }

            override fun onFailure(throwable: Throwable) {

            }
        })

        request.enqueue()

    }

    fun getCustomFeeds(category: String?) {

        val call = RetrofitClient.getApiInterface().getCustomFeeds(category!!)

        request = RetrofitRequest(call, object : ResponseCallback<Response<MutableList<Article>>> {

            override fun onSuccess(response: Response<MutableList<Article>>) {

                customNews.value = response.articles

            }

            override fun onError(errorDescription: String) {

            }

            override fun onFailure(throwable: Throwable) {

            }
        })

        request.enqueue()

    }

}