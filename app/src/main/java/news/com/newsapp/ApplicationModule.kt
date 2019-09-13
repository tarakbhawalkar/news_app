package news.com.newsapp

import android.app.Application
import android.content.Context


/**
 * Created by Tarak on 24,May,2018
 *
 */
class ApplicationModule {

    fun getApplicationContext(application: Application): Context {

        return application
    }
}