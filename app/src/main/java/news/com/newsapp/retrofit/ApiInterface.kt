package news.com.newsapp.retrofit

import news.com.newsapp.home.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Tarak on 20,April,2018
 *
 */
interface ApiInterface {

    @GET("top-headlines/")
    fun getNewsFeeds(@Query("category") category: String?,
                     @Query("country") country: String = "in",
                     @Query("language") language: String = "en"): Call<Response<MutableList<Article>>>


    @GET("everything")
    fun getCustomFeeds(@Query("q") query: String,
                       @Query("language") language: String = "en"): Call<Response<MutableList<Article>>>
}