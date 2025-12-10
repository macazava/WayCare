package pt.iade.ei.waycareapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object Utils {

    fun bitmapToUri(context: Context, bitmap: Bitmap): Uri? {
        return try {
            // Cria um arquivo temporário
            val file = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
            Uri.fromFile(file)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
