package news.com.newsapp.home.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by Tarak on 23,April,2018
 *
 */
data class Article(@SerializedName("author") val author: String,
                   @SerializedName("title") val title: String,
                   @SerializedName("description") val description: String,
                   @SerializedName("url") val url: String,
                   @SerializedName("urlToImage") val urlToImage: String,
                   @SerializedName("publishedAt") val publishedAt: String,
                   @SerializedName("source") val source: Source) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Source::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeParcelable(source, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}