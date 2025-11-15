package pt.iade.ei.waycareapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance

class ReporteViewModel : ViewModel() {

    private val _reportes = MutableStateFlow<List<Reporte>>(emptyList())
    val reportes: StateFlow<List<Reporte>> = _reportes

    // Buscar todos os reportes
    fun carregarReportes() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getReportes()
                if (response.isSuccessful) {
                    _reportes.value = response.body().orEmpty()
                    Log.d("API", "Reportes carregados: ${_reportes.value.size}")
                } else {
                    Log.e("API", "Erro ao buscar reportes: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Falha ao buscar reportes: ${e.message}")
            }
        }
    }

    //Função única para enviar um reporte
    fun enviarReporte(reporte: Reporte) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.criarReporte(
                    utiId = reporte.rep_uti_id.uti_id!!,
                    anoId = reporte.rep_ano_id.ano_id,
                    reporte = reporte
                )
                if (response.isSuccessful) {
                    Log.d("API", "Reporte enviado com sucesso: ${response.body()}")
                } else {
                    Log.e("API", "Erro ao enviar reporte: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Falha na ligação: ${e.message}")
            }
        }
    }
}

