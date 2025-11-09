package pt.iade.ei.waycareapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.util.Log


class ReporteViewModel : ViewModel() {

    // Metodo para guardar ou processar o reporte
    fun guardarReporte(reporte: Reporte) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.enviarReporte(reporte)
                if (response.isSuccessful) {
                    Log.d("ReporteViewModel", "Reporte enviado com sucesso: ${response.body()}")
                } else {
                    Log.e("ReporteViewModel", "Erro ao enviar reporte: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ReporteViewModel", "Exceção ao enviar reporte: ${e.message}")
            }
        }
    }
}
