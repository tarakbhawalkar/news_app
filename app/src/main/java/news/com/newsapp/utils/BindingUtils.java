package news.com.newsapp.utils;


import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import news.com.newsapp.R;

public class BindingUtils {

    @BindingAdapter({"url"})
    public static void loadImage(ImageView view, String url) {
        if (null != url) {
            Glide.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(view);
        }
    }

    @BindingAdapter("setFormattedTime")
    public static void setTime(TextView textView, String timeStamp){
        if(!TextUtils.isEmpty(timeStamp)){
            textView.setText(TimeManager.Companion.formatDate(timeStamp));
        }else {
            textView.setVisibility(View.GONE);
        }
    }
}
