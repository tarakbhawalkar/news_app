package news.com.newsapp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import news.com.newsapp.utils.ProgressDialog


/**
 * Created by Tarak on 24,April,2018
 *
 */
open class BaseActivity : AppCompatActivity() {


    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog = ProgressDialog(this)
    }

    fun showProgress() {
        progressDialog.show()
    }

    fun hideProgress() {
        progressDialog.dismiss()
    }

}