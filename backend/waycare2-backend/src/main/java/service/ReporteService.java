package service;


import com.waycare.waycare2.Model.Reporte;
import com.waycare.waycare2.Repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public class ReporteService {

        private final ReporteRepository.reporteRepository reporteRepository;

        @Autowired
        public ReporteService(ReporteRepository.reporteRepository reporteRepository) {
            this.reporteRepository = reporteRepository;
        }

        public Reporte criar(Reporte reporte) {
            return reporteRepository.save(reporte);
        }

        public List<Reporte> listarTodos() {
            return reporteRepository.findAll();
        }

        public Optional<Reporte> buscarPorId(Long id) {
            return reporteRepository.findById(id);
        }

        public List<Reporte> listarPorUtilizador(Long utilizadorId) {
            return reporteRepository.findByUtilizador_Id(utilizadorId);
        }

        public void apagar(Long id) {
            reporteRepository.deleteById(id);
        }
}
