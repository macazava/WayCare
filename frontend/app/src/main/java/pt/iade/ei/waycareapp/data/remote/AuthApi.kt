package pt.iade.ei.waycareapp.data.remote

import pt.iade.ei.waycareapp.data.model.Utilizador
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    // Registo de utilizador
    @POST("api/utilizadores/registar")
    suspend fun register(@Body user: Utilizador): Response<Utilizador>

    // Login de utilizador
    @POST("api/utilizadores/login")
    suspend fun login(@Body user: Utilizador): Response<String>
}


