package pt.iade.ei.waycareapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://teu-backend.com/api/" // substitui pelo teu URL real

    val api: ReporteApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReporteApi::class.java)
    }
}
