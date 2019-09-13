package news.com.newsapp.home.model

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by Tarak on 25,April,2018
 *
 */
data class ArticleList(val category: String, val articleList: List<Article>) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createTypedArrayList(Article)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeTypedList(articleList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticleList> {
        override fun createFromParcel(parcel: Parcel): ArticleList {
            return ArticleList(parcel)
        }

        override fun newArray(size: Int): Array<ArticleList?> {
            return arrayOfNulls(size)
        }
    }
}