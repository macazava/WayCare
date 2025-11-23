package pt.iade.ei.waycareapp.data.remote

import pt.iade.ei.waycareapp.data.model.TipoAnomalia
import retrofit2.http.GET
import retrofit2.Response


interface TipoAnomaliaApi {
    @GET("api/TipoAnomalia")
    suspend fun listarTipos(): Response<List<TipoAnomalia>>
}
