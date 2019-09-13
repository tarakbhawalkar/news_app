package news.com.newsapp.retrofit

import com.google.gson.annotations.SerializedName


/**
 * Created by Tarak on 23,April,2018
 *
 */
class Response<T> {

    @SerializedName("status")
    lateinit var isSuccess: String

    @SerializedName("totalResults")
    var totalResults: Long = 0

    @SerializedName("articles")
    val articles: T? = null

    val category: String? = null


}