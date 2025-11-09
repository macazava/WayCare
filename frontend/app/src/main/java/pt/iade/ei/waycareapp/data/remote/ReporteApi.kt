package pt.iade.ei.waycareapp.data.remote

import pt.iade.ei.waycareapp.data.model.Reporte
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReporteApi {
    @POST("reportes/utilizador/{utiId}/{anoId}")
    suspend fun criarReporte(
        @Path("utiId") utiId: Long,
        @Path("anoId") anoId: Long,
        @Body reporte: Reporte
    ): Response<Reporte>
}

