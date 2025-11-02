package com.example.waycare.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;



@Component
public class GoogleMapsUtil {

    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    //Converte coordenadas → morada
    public String getAddressFromCoordinates(double lat, double lng) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                + lat + "," + lng + "&key=" + apiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject json = new JSONObject(response.getBody());

        if (json.getJSONArray("results").isEmpty()) {
            throw new RuntimeException("Morada não encontrada");
        }

        return json.getJSONArray("results")
                .getJSONObject(0)
                .getString("formatted_address");
    }

    //Converte morada → coordenadas
    public double[] getCoordinatesFromAddress(String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
                + address.replace(" ", "+") + "&key=" + apiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        JSONObject json = new JSONObject(response.getBody());

        if (json.getJSONArray("results").isEmpty()) {
            throw new RuntimeException("Endereço não encontrado");
        }

        JSONObject location = json
                .getJSONArray("results")
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location");

        return new double[]{location.getDouble("lat"), location.getDouble("lng")};
    }
}
