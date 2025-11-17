package com.example.waycare.Controller;

import com.example.waycare.utils.ExifUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/exif")
public class ExifController {

    @PostMapping
    public ResponseEntity<?> lerExif(@RequestParam("imagem") MultipartFile imagem) {
        try {
            File temp = File.createTempFile("exif_", imagem.getOriginalFilename());
            imagem.transferTo(temp);

            double[] coordenadas = ExifUtil.extrairCoordenadas(temp);
            LocalDate data = ExifUtil.extrairData(temp);

            return ResponseEntity.ok(
                    Map.of(
                            "latitude", coordenadas != null ? coordenadas[0] : null,
                            "longitude", coordenadas != null ? coordenadas[1] : null,
                            "data", data != null ? data.toString() : null
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro: " + e.getMessage());
        }
    }
}
