package news.com.newsapp.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Tarak on 27,April,2018
 *
 */
class TimeManager {

    companion object {

        const val yyyyMMddTHHmmssZ = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val EMMMdd = "E, MMM dd"

        fun formatDate(inputDate: String): String {

            val inputFormat = SimpleDateFormat(yyyyMMddTHHmmssZ, Locale.getDefault())

            val outputFormat = SimpleDateFormat(EMMMdd, Locale.getDefault())

            return outputFormat.format(inputFormat.parse(inputDate))
        }
    }
}