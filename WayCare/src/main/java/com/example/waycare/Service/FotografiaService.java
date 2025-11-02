package com.example.waycare.Service;

import com.example.waycare.Repository.FotografiaRepository;
import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.models.Fotografia;
import com.example.waycare.models.Localizacao;
import com.example.waycare.models.Reporte;
import com.example.waycare.utils.ExifUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FotografiaService {

    @Autowired
    private FotografiaRepository fotografiaRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Fotografia> listarTodos() {
        return fotografiaRepository.findAll();
    }

    public Optional<Fotografia> procurarPorId(Long id) {
        return fotografiaRepository.findById(id);
    }

    public Fotografia criar(Fotografia fotografia) {
        return fotografiaRepository.save(fotografia);
    }

    public Fotografia atualizar(Long id, Fotografia fotografia) {
        Fotografia existente = fotografiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fotografia não encontrada"));
        existente.setUrl(fotografia.getUrl());
        existente.setCaminho(fotografia.getCaminho());
        existente.setMime(fotografia.getMime());
        existente.setTamanho(fotografia.getTamanho());
        existente.setNome(fotografia.getNome());
        existente.setReporte(fotografia.getReporte());
        return fotografiaRepository.save(existente);
    }

    public void eliminar(Long id) {
        fotografiaRepository.deleteById(id);
    }

    public Fotografia criarComUpload(Long reporteId, MultipartFile file) throws IOException {

        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));

        String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
        File dir = new File(uploadDir);

        // Cria o diretorio onde são armazenadas as fotos(Erro anterior)
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File destino = new File(dir, file.getOriginalFilename());

        file.transferTo(destino);

        double[] coords = ExifUtil.extrairCoordenadas(destino);
        LocalDate dataExif = ExifUtil.extrairData(destino);

        if (coords != null) {
            Localizacao loc = reporte.getLocalizacao();
            if (loc == null) {
                loc = new Localizacao();
            }
            loc.setLatitude(coords[0]);
            loc.setLongitude(coords[1]);
            reporte.setLocalizacao(loc);
        }

        if (dataExif != null) {
            reporte.setData(dataExif);
        }

        reporteRepository.save(reporte);

        Fotografia foto = new Fotografia();
        foto.setNome(file.getOriginalFilename());
        foto.setMime(file.getContentType());
        foto.setTamanho(file.getSize());
        foto.setCaminho(destino.getAbsolutePath());
        foto.setUrl("/uploads/" + file.getOriginalFilename());
        foto.setReporte(reporte);

        return fotografiaRepository.save(foto);
    }
}


