package com.example.waycare.config.utils;

import com.example.waycare.exceptions.EnderecoNaoEncontradoException;
import com.example.waycare.exceptions.GoogleMapsApiException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class GoogleMapsUtil {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${google.maps.api.key}")
    private String apiKey;

    public String getAddressFromCoordinates(double lat, double lng) {
        try {
            String latStr = String.format(java.util.Locale.US, "%.6f", lat);
            String lngStr = String.format(java.util.Locale.US, "%.6f", lng);

            String url = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("maps.googleapis.com")
                    .path("/maps/api/geocode/json")
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

    public List<double[]> getRoute(double origemLat, double origemLng,
                                   double destinoLat, double destinoLng) {
        try {
            String origin = String.format(java.util.Locale.US, "%.6f,%.6f", origemLat, origemLng);
            String destination = String.format(java.util.Locale.US, "%.6f,%.6f", destinoLat, destinoLng);

            String url = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("maps.googleapis.com")
                    .path("/maps/api/directions/json")
                    .queryParam("origin", origin)
                    .queryParam("destination", destination)
                    .queryParam("mode", "driving")
                    .queryParam("key", apiKey)
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JSONObject json = new JSONObject(response.getBody());

            String status = json.getString("status");
            if (!"OK".equals(status)) {
                throw new GoogleMapsApiException("Não foi possível obter rota. Status: " + status);
            }

            String polyline = json
                    .getJSONArray("routes")
                    .getJSONObject(0)
                    .getJSONObject("overview_polyline")
                    .getString("points");

            return decodePolyline(polyline);

        } catch (RestClientException e) {
            throw new GoogleMapsApiException("Erro ao comunicar com Google Directions API", e);
        } catch (Exception e) {
            throw new GoogleMapsApiException("Erro inesperado ao calcular rota", e);
        }
    }

    private List<double[]> decodePolyline(String encoded) {
        List<double[]> path = new ArrayList<>();

        int index = 0;
        int len = encoded.length();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;

            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlat = ((result & 1) != 0) ? ~(result >> 1) : (result >> 1);
            lat += dlat;

            shift = 0;
            result = 0;

            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlng = ((result & 1) != 0) ? ~(result >> 1) : (result >> 1);
            lng += dlng;

            double latD = lat / 1E5;
            double lngD = lng / 1E5;
            path.add(new double[]{latD, lngD});
        }

        return path;
    }
}
