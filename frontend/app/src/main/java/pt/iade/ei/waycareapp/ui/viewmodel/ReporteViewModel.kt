package pt.iade.ei.waycareapp.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.*
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance
import pt.iade.ei.waycareapp.utils.CloudinaryHelper
import retrofit2.Response

class ReporteViewModel : ViewModel() {

    private val _reportes = MutableStateFlow<List<Reporte>>(emptyList())
    val reportes: StateFlow<List<Reporte>> = _reportes

    private val _tiposAnomalia = MutableStateFlow<List<TipoAnomalia>>(emptyList())
    val tiposAnomalia: StateFlow<List<TipoAnomalia>> = _tiposAnomalia

    private val _anomalias = MutableStateFlow<List<Anomalia>>(emptyList())
    val anomalias: StateFlow<List<Anomalia>> = _anomalias

    private val _fotoUrl = MutableStateFlow<String?>(null)
    val fotoUrl: StateFlow<String?> = _fotoUrl

    private val fotografiaApi = RetrofitInstance.fotografiaApi


    //Metodo para subir foto via Cloudinary
    fun uploadFoto(uri: Uri, context: Context) {
        CloudinaryHelper.uploadImage(uri) { url ->
            if (url != null) {
                _fotoUrl.value = url
                Log.d("ReporteVM", "Foto enviada com sucesso: $url")
            } else {
                Log.e("ReporteVM", "Erro ao enviar foto")
            }
        }
    }

    fun carregarReportes() {
        viewModelScope.launch {
            try {
                val response: Response<List<Reporte>> = RetrofitInstance.api.getReportes()
                if (response.isSuccessful) {
                    _reportes.value = response.body().orEmpty()
                    Log.d("ReporteVM", "Reportes carregados: ${_reportes.value.size}")
                } else {
                    Log.e("ReporteVM", "Erro ao buscar reportes: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteVM", "Falha ao buscar reportes: ${e.message}")
            }
        }
    }

    fun carregarTiposAnomalia() {
        viewModelScope.launch {
            try {
                val response: Response<List<TipoAnomalia>> = RetrofitInstance.tipoAnomaliaApi.listarTipos()
                if (response.isSuccessful) {
                    _tiposAnomalia.value = response.body().orEmpty()
                    Log.d("ReporteVM", "Tipos de anomalia carregados: ${_tiposAnomalia.value.size}")
                } else {
                    Log.e("ReporteVM", "Erro ao buscar tipos: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteVM", "Falha ao buscar tipos: ${e.message}")
            }
        }
    }

    fun enviarReporte(request: ReporteRequest) {
        viewModelScope.launch {
            try {
                Log.d("ReporteAPI", "A enviar reporte: $request")

                val response = RetrofitInstance.api.criarReporte(request)

                if (response.isSuccessful) {
                    Log.d("ReporteAPI", "Sucesso: ${response.body()}")
                } else {
                    Log.e("ReporteAPI", "Erro: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteAPI", "Falha: ${e.message}")
            }
        }
    }
}

