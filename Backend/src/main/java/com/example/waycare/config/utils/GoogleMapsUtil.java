package com.example.waycare.config.utils;

import com.example.waycare.exceptions.EnderecoNaoEncontradoException;
import com.example.waycare.exceptions.GoogleMapsApiException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class GoogleMapsUtil {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAddressFromCoordinates(double lat, double lng) {
        try {
            String latStr = String.format(java.util.Locale.US, "%.6f", lat);
            String lngStr = String.format(java.util.Locale.US, "%.6f", lng);

            String url = UriComponentsBuilder
                    .fromHttpUrl("https://maps.googleapis.com/maps/api/geocode/json")
                    .queryParam("latlng", latStr + "," + lngStr)
                    .queryParam("key", apiKey)
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JSONObject json = new JSONObject(response.getBody());

            String status = json.getString("status");
            if (!"OK".equals(status)) {
                throw new EnderecoNaoEncontradoException("Morada não encontrada para: " + lat + ", " + lng);
            }

            return json.getJSONArray("results")
                    .getJSONObject(0)
                    .getString("formatted_address");

        } catch (RestClientException e) {
            throw new GoogleMapsApiException("Erro ao comunicar com Google Maps API", e);
        } catch (Exception e) {
            if (e instanceof EnderecoNaoEncontradoException) throw e;
            throw new GoogleMapsApiException("Erro inesperado ao processar coordenadas", e);
        }
    }

    public double[] getCoordinatesFromAddress(String address) {
        try {
            if (address == null || address.trim().isEmpty()) {
                throw new IllegalArgumentException("A morada não pode ser vazia");
            }

            String url = UriComponentsBuilder
                    .fromHttpUrl("https://maps.googleapis.com/maps/api/geocode/json")
                    .queryParam("address", address)
                    .queryParam("key", apiKey)
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JSONObject json = new JSONObject(response.getBody());

            String status = json.getString("status");
            if (!"OK".equals(status)) {
                throw new EnderecoNaoEncontradoException("Endereço não encontrado: " + address);
            }

            JSONObject location = json
                    .getJSONArray("results")
                    .getJSONObject(0)
                    .getJSONObject("geometry")
                    .getJSONObject("location");

            return new double[]{location.getDouble("lat"), location.getDouble("lng")};

        } catch (RestClientException e) {
            throw new GoogleMapsApiException("Erro ao comunicar com Google Maps API", e);
        } catch (Exception e) {
            if (e instanceof EnderecoNaoEncontradoException || e instanceof IllegalArgumentException) throw e;
            throw new GoogleMapsApiException("Erro inesperado ao processar endereço", e);
        }
    }

    public List<double[]> getRoute(double origemLat, double origemLon, double destinoLat, double destinoLon) {
        return null;
    }
}
