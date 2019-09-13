package news.com.newsapp.retrofit

import news.com.newsapp.BuildConfig
import news.com.newsapp.base.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Tarak on 20,April,2018
 *
 */
class RetrofitClient {


    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://newsapi.org/v2/"

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()


        val apiKeyInterceptor = Interceptor { chain ->
            val original = chain.request()

            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(Constants.NEWS_API_KEY, BuildConfig.NEWS_API_KEY)
                    .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                    .url(url)
            val request = requestBuilder.build()


            chain.proceed(request)
        }

        httpClient.addInterceptor(loggingInterceptor)
        httpClient.addInterceptor(apiKeyInterceptor)

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    companion object {

        private var sInstance: RetrofitClient? = null


        fun create() {
            sInstance = RetrofitClient()
        }

        fun retrofit(): Retrofit? {
            return sInstance!!.retrofit
        }


        fun getApiInterface(): ApiInterface {
            return retrofit()!!.create(ApiInterface::class.java)
        }

    }
}