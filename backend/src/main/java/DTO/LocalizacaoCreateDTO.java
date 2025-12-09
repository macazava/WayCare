package DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
 @Data
public class LocalizacaoCreateDTO {
     @NotNull(message = "Latitude é obrigatória.")
     private Double latitude;

     @NotNull(message = "Longitude é obrgatória.")
     private Double longitude;

     private String descricao;

 }