package pt.iade.ei.waycareapp.data.remote

import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.model.ReporteDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReporteApi {
    // GET: buscar todos os reportes
    @GET("reportes")
    suspend fun getReportes(): Response<List<Reporte>>

    // POST: criar um novo reporte
    @POST("api/reportes/utilizador/{utiId}/{anoId}")
    suspend fun criarReporte(
        @Path("utiId") utiId: Long,
        @Path("anoId") anoId: Long,
        @Body reporte: ReporteDTO
    ): Response<Reporte>
}
