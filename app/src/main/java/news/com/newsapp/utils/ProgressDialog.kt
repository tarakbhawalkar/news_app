package news.com.newsapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import news.com.newsapp.R

class ProgressDialog(context: Context) : Dialog(context) {

    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_loading_dialog)
        setCancelable(false)
    }

    companion object {

        private val mContext: Context? = null
        private val mInstance: ProgressDialog? = null
    }
}