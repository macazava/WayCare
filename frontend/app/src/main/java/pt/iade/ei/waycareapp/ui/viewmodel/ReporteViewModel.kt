package pt.iade.ei.waycareapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import pt.iade.ei.waycareapp.data.model.Reporte

class ReporteViewModel : ViewModel() {

    // Lista local temporária (podes substituir por chamada à API depois)
    private val listaReportes = mutableListOf<Reporte>()

    fun guardarReporte(reporte: Reporte) {
        listaReportes.add(reporte)
    }

    fun getReporteById(id: Long): Reporte? {
        return listaReportes.find { it.id == id }
    }
}
