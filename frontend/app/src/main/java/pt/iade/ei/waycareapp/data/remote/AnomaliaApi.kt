package pt.iade.ei.waycareapp.data.remote

import pt.iade.ei.waycareapp.data.model.Anomalia
import retrofit2.Response
import retrofit2.http.GET

interface AnomaliaApi {
    @GET("api/anomalia")
    suspend fun listarAnomalias(): Response<List<Anomalia>>
}
