package news.com.newsapp

import android.app.Application
import news.com.newsapp.retrofit.RetrofitClient


/**
 * Created by Tarak on 23,April,2018
 *
 */
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.create()
    }
}
