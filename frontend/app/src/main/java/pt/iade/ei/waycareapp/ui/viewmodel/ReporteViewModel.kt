package pt.iade.ei.waycareapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.model.TipoAnomalia
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance
import retrofit2.Response

class ReporteViewModel : ViewModel() {

    // 🔹 Lista de reportes recebidos do backend
    private val _reportes = MutableStateFlow<List<Reporte>>(emptyList())
    val reportes: StateFlow<List<Reporte>> = _reportes

    // 🔹 Lista de tipos de anomalia para preencher o dropdown
    private val _tiposAnomalia = MutableStateFlow<List<TipoAnomalia>>(emptyList())
    val tiposAnomalia: StateFlow<List<TipoAnomalia>> = _tiposAnomalia

    // 🔹 Carregar todos os reportes
    fun carregarReportes() {
        viewModelScope.launch {
            try {
                val response: Response<List<Reporte>> = RetrofitInstance.api.getReportes()
                if (response.isSuccessful) {
                    _reportes.value = response.body().orEmpty()
                    Log.d("ReporteVM", "✅ Reportes carregados: ${_reportes.value.size}")
                } else {
                    Log.e("ReporteVM", "❌ Erro ao buscar reportes: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteVM", "❌ Falha ao buscar reportes: ${e.message}")
            }
        }
    }

    // 🔹 Carregar tipos de anomalia para o dropdown
    fun carregarTiposAnomalia() {
        viewModelScope.launch {
            try {
                val response: Response<List<TipoAnomalia>> = RetrofitInstance.tipoAnomaliaApi.listarTipos()
                if (response.isSuccessful) {
                    _tiposAnomalia.value = response.body().orEmpty()
                    Log.d("ReporteVM", "✅ Tipos de anomalia carregados: ${_tiposAnomalia.value.size}")
                } else {
                    Log.e("ReporteVM", "❌ Erro ao buscar tipos: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteVM", "❌ Falha ao buscar tipos: ${e.message}")
            }
        }
    }

    // 🔹 Enviar um reporte para o backend
    fun enviarReporte(reporte: Reporte) {
        viewModelScope.launch {
            val utiId = reporte.rep_uti_id?.uti_id
            val anoId = reporte.rep_ano_id?.ano_id

            Log.d("ReporteAPI", "🧾 utiId do utilizador logado: $utiId")
            Log.d("ReporteAPI", "🧾 anoId da anomalia selecionada: $anoId")

            if (utiId == null || anoId == null) {
                Log.e("ReporteAPI", "❌ utiId ou anoId estão nulos — não é possível enviar reporte")
                return@launch
            }

            try {
                Log.d("ReporteAPI", "📤 A enviar reporte com utiId=$utiId e anoId=$anoId")
                Log.d("ReporteAPI", "📦 Conteúdo do reporte: $reporte")

                val response: Response<Reporte> = RetrofitInstance.api.criarReporte(
                    utiId = utiId,
                    anoId = anoId,
                    reporte = reporte
                )

                if (response.isSuccessful) {
                    Log.d("ReporteAPI", "✅ Reporte enviado com sucesso: ${response.body()}")
                } else {
                    Log.e("ReporteAPI", "❌ Erro ao enviar reporte: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteAPI", "❌ Falha na ligação: ${e.message}")
            }
        }
    }
}


