package pt.iade.ei.waycareapp.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.UploadCallback
import com.cloudinary.android.callback.ErrorInfo

object CloudinaryHelper {

    fun init(context: Context) {
        val config = mapOf(
            "cloud_name" to "dn511niyi",        // teu CLOUD_NAME
            "api_key" to "",                     // unsigned
            "api_secret" to ""                   // unsigned
        )
        MediaManager.init(context, config)
    }

    fun uploadImage(uri: Uri, onResult: (url: String?) -> Unit) {
        MediaManager.get().upload(uri)
            .unsigned("waycare_unsigned")   // teu UPLOAD_PRESET
            .callback(object : UploadCallback {
                override fun onStart(requestId: String?) {
                    Log.d("Cloudinary", "Upload iniciado")
                }

                override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                    // opcional: progresso do upload
                }

                override fun onSuccess(requestId: String?, resultData: Map<*, *>?) {
                    val url = resultData?.get("secure_url") as? String
                    Log.d("Cloudinary", "Upload concluído: $url")
                    onResult(url)
                }

                override fun onError(requestId: String?, error: ErrorInfo?) {
                    Log.e("Cloudinary", "Erro no upload: ${error?.description}")
                    onResult(null)
                }

                override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                    Log.e("Cloudinary", "Upload reagendado: ${error?.description}")
                    onResult(null)
                }
            })
            .dispatch()
    }
}
