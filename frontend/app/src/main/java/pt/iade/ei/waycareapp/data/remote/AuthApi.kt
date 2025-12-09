package pt.iade.ei.waycareapp.data.remote

import okhttp3.ResponseBody
import pt.iade.ei.waycareapp.data.model.LoginRequest
import pt.iade.ei.waycareapp.data.model.RegisterRequest
import pt.iade.ei.waycareapp.data.model.Utilizador
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    // Registo de utilizador
    @POST("utilizadores/register")
    suspend fun register(@Body request: RegisterRequest): Response<Utilizador>

    // Login de utilizador
    @POST("utilizadores/login")
    suspend fun login(@Body request: LoginRequest): Response<Utilizador>
}



