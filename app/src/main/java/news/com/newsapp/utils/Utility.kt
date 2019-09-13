package news.com.newsapp.utils

import android.graphics.Bitmap
import android.view.View
import java.io.File
import java.io.FileOutputStream
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider


/**
 * Created by Tarak on 30,April,2018
 *
 */
class Utility {

    companion object {
        fun takeScreenShot(view: View): Bitmap {

            view.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(view.drawingCache)
            view.isDrawingCacheEnabled = false
            return bitmap

        }

        fun store(bm: Bitmap, context: Context): String {
            val dirPath = context.getExternalFilesDir(null).toString() + "/Screenshot"
            val dir = File(dirPath)
            if (!dir.exists())
                dir.mkdirs()
            val file = File(dirPath, System.currentTimeMillis().toString() + ".png")
            try {
                val fOut = FileOutputStream(file)
                bm.compress(Bitmap.CompressFormat.PNG, 100, fOut)
                fOut.flush()
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return file.path

        }

        fun shareImage(file: File, link: String, context: Context) {
            var uri: Uri
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(context,
                        context.getPackageName() + ".provider", file)
            } else {
                uri = Uri.fromFile(file)
            }

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "image/*"

            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "")
            intent.putExtra(android.content.Intent.EXTRA_TEXT, "For more details click on $link")
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            try {
                context.startActivity(Intent.createChooser(intent, "Share via..."))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "No App Available", Toast.LENGTH_SHORT).show()
            }

        }
    }
}