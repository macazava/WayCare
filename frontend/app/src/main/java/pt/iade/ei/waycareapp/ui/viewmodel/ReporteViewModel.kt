package pt.iade.ei.waycareapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.Anomalia
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.model.TipoAnomalia
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance
import retrofit2.Response

class ReporteViewModel : ViewModel() {

    private val _reportes = MutableStateFlow<List<Reporte>>(emptyList())
    val reportes: StateFlow<List<Reporte>> = _reportes

    private val _tiposAnomalia = MutableStateFlow<List<TipoAnomalia>>(emptyList())
    val tiposAnomalia: StateFlow<List<TipoAnomalia>> = _tiposAnomalia

    private val _anomalias = MutableStateFlow<List<Anomalia>>(emptyList())
    val anomalias: StateFlow<List<Anomalia>> = _anomalias

    fun carregarAnomalias() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.anomaliaApi.listarAnomalias()
                if (response.isSuccessful) {
                    _anomalias.value = response.body().orEmpty()
                    Log.d("ReporteVM", "✅ Anomalias carregadas: ${_anomalias.value.size}")
                } else {
                    Log.e("ReporteVM", "❌ Erro ao buscar anomalias: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteVM", "❌ Falha ao buscar anomalias: ${e.message}")
            }
        }
    }
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

    fun enviarReporte(reporte: Reporte) {
        viewModelScope.launch {
            val utiId = reporte.rep_uti_id?.uti_id
            val anoId = reporte.rep_ano_id?.ano_id

            Log.d("ReporteAPI", "🧾 utiId: $utiId")
            Log.d("ReporteAPI", "🧾 anoId: $anoId")

            if (utiId == null || anoId == null || anoId == 0L) {
                Log.e("ReporteAPI", "❌ utiId ou anoId inválidos — não é possível enviar")
                return@launch
            }

            try {
                val reporteBody = reporte.copy(
                    rep_uti_id = null,
                    rep_ano_id = null
                )

                Log.d("ReporteAPI", "📤 A enviar reporte com utiId=$utiId e anoId=$anoId")
                Log.d("ReporteAPI", "📦 Body: $reporteBody")

                val response = RetrofitInstance.api.criarReporte(utiId, anoId, reporteBody)

                if (response.isSuccessful) {
                    Log.d("ReporteAPI", "✅ Sucesso: ${response.body()}")
                } else {
                    Log.e("ReporteAPI", "❌ Erro: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteAPI", "❌ Falha: ${e.message}")
            }
        }
    }
}

