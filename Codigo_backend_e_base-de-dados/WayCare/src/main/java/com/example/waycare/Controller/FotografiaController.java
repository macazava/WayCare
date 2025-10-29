package com.example.waycare.Controller;

import com.example.waycare.Service.FotografiaService;
import com.example.waycare.Service.ReporteService;
import com.example.waycare.models.Fotografia;
import com.example.waycare.models.Localizacao;
import com.example.waycare.models.Reporte;
import com.example.waycare.utils.ExifUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fotografias")
@CrossOrigin(origins = "*")
public class FotografiaController {

    @Autowired
    private FotografiaService fotografiaService;

    @Autowired
    private ReporteService reporteService;

    // ✅ Novo endpoint — upload de foto com extração EXIF automática
    @PostMapping("/upload/{reporteId}")
    public ResponseEntity<Fotografia> uploadFotografia(
            @PathVariable Long reporteId,
            @RequestParam("imagem") MultipartFile imagem) throws IOException {

        // 1️⃣ Guardar o ficheiro localmente (pasta temporária)
        String caminho = System.getProperty("user.dir") + "/uploads/" + imagem.getOriginalFilename();
        File ficheiro = new File(caminho);
        imagem.transferTo(ficheiro);

        // 2️⃣ Extrair metadados EXIF
        double[] coordenadas = ExifUtil.extrairCoordenadas(ficheiro);

        // 3️⃣ Obter o reporte associado
        Optional<Reporte> optReporte = reporteService.procurarPorId(reporteId);
        if (optReporte.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Reporte reporte = optReporte.get();

        // 4️⃣ Se tiver coordenadas, criar Localização
        if (coordenadas != null) {
            Localizacao loc = new Localizacao();
            loc.setLatitude(coordenadas[0]);
            loc.setLongitude(coordenadas[1]);
            reporte.setLocalizacao(loc);
        }

        // 5️⃣ Criar objeto Fotografia
        Fotografia foto = new Fotografia();
        foto.setNome(imagem.getOriginalFilename());
        foto.setCaminho(caminho);
        foto.setReporte(reporte);

        // 6️⃣ Guardar no banco de dados
        Fotografia salva = fotografiaService.criar(foto);
        return ResponseEntity.ok(salva);
    }

    // Métodos CRUD normais
    @GetMapping
    public ResponseEntity<List<Fotografia>> listarTodos() {
        return ResponseEntity.ok(fotografiaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Fotografia>> procurarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(fotografiaService.procurarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Fotografia> criar(@RequestBody Fotografia fotografia) {
        Fotografia nova = fotografiaService.criar(fotografia);
        return ResponseEntity.ok(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fotografia> atualizar(@PathVariable Long id, @RequestBody Fotografia fotografia) {
        try {
            Fotografia atualizada = fotografiaService.atualizar(id, fotografia);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            fotografiaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
