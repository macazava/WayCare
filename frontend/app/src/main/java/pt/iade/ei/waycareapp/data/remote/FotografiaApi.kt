package pt.iade.ei.waycareapp.data.remote

import pt.iade.ei.waycareapp.data.model.Fotografia
import pt.iade.ei.waycareapp.data.model.FotografiaRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FotografiaApi {

    @POST("fotografias")
    suspend fun criarFotografia(@Body request: FotografiaRequest): Response<Fotografia>

    @GET("fotografias/reporte/{repId}")
    suspend fun listarPorReporte(@Path("repId") reporteId: Long): Response<List<Fotografia>>
}
