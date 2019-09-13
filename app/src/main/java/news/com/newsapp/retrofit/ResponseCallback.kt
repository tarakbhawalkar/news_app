package news.com.newsapp.retrofit


/**
 * Created by Tarak on 23,April,2018
 *
 */
interface ResponseCallback<in T> {

    fun onSuccess(response: T)

    fun onError(errorDescription: String)

    fun onFailure(throwable: Throwable)
}