package pt.iade.ei.waycareapp.data.remote

import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.model.ReporteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ReporteApi {
    @GET("reportes")
    suspend fun getReportes(): Response<List<Reporte>>

    @POST("reportes/criar")
    suspend fun criarReporte(@Body request: ReporteRequest): Response<Reporte>
}

