package news.com.newsapp.retrofit


import retrofit2.Call
import retrofit2.Callback


class RetrofitRequest<T>(private var call: Call<T>?, private val responseListener: ResponseCallback<T>) : Request() {

    override fun enqueue() {
        call!!.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                if (response.isSuccessful)
                    responseListener.onSuccess(response.body()!!)
                else {
                    responseListener.onError(response.message())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (call.isCanceled)
                    return
                responseListener.onFailure(t)
            }
        })
    }

    override fun cancel() {
        call!!.cancel()
    }

    override fun retry() {
        call = call!!.clone()
        enqueue()
    }

}
