package pt.iade.ei.waycareapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //API para reportes
    val api: ReporteApi by lazy {
        retrofit.create(ReporteApi::class.java)
    }

    //API para autenticação (login e registo)
    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    //API para tipoanomalia
    val tipoAnomaliaApi: TipoAnomaliaApi by lazy {
        retrofit.create(TipoAnomaliaApi::class.java)
    }

    //API para anomalias
    val anomaliaApi: AnomaliaApi by lazy {
        retrofit.create(AnomaliaApi::class.java)
    }


}
