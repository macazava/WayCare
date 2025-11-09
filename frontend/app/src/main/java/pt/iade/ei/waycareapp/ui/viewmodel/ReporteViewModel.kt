package pt.iade.ei.waycareapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.*
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance


class ReporteViewModel : ViewModel() {

    fun enviarReporte() {
        Log.d("ReporteViewModel", "Função enviarReporte chamada")
        viewModelScope.launch {
            try {
                val novoReporte = Reporte(
                    rep_id = 0,
                    rep_uti_id = Utilizador(uti_id = 1, uti_nome = "Maria", uti_email = "maria123@gmail.com", uti_password = "123"),
                    rep_ano_id = Anomalia(ano_id = 2, tip_id = TipoAnomalia(tip_id = 1, tip_nome = "buraco"), ano_descricao = "Infraestrutura", ano_grau_perigo = "alto"),
                    rep_tipo_personalizado = "Buraco profundo",
                    rep_loc_id = Localizacao(loc_id = 1, loc_latitude = 38.7, loc_longitude = -9.0, loc_endereco = "Av. da liberdade 123" ),
                    fotografia = Fotografia(foto_id = 1, foto_nome = "buraco", foto_rep_id = 1, foto_url = "url da foto", foto_caminho = "caminho da foto", foto_mime = "mime da foto", foto_tamanho = 38),
                    rep_estado = "pendente",
                    rep_data = "2025-11-09",
                    rep_descricao = "Buraco na estrada junto à escola"
                )

                val response = RetrofitInstance.api.criarReporte(
                    utiId = novoReporte.rep_uti_id.uti_id,
                    anoId = novoReporte.rep_ano_id.ano_id,
                    reporte = novoReporte
                )

                if (response.isSuccessful) {
                    Log.d("API", "Reporte enviado com sucesso: ${response.body()}")
                } else {
                    Log.e("API", "Erro ao enviar: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Falha: ${e.message}")
            }
        }
    }
    fun guardarReporte(reporte: Reporte) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.criarReporte(
                    utiId = reporte.rep_uti_id.uti_id,
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
