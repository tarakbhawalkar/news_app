package news.com.newsapp.retrofit


/**
 * Created by Tarak on 23,April,2018
 *
 */

abstract class Request {

    abstract fun enqueue()

    abstract fun cancel()

    abstract fun retry()

}