package com.kuiko.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuiko.api.model.Capital;
import com.kuiko.api.model.Community;
import com.kuiko.api.model.WeatherDTO;
import com.kuiko.api.repository.CommunityRepository;

import reactor.core.publisher.Mono;


@Service
public class OpenWeatherService {

    @Autowired
    private CommunityRepository communityRepository;

    private final WebClient webClient;

    private final String API_KEY = "c249d0e0ed4e73e56f5140374f50f051";


    public OpenWeatherService(WebClient.Builder webClientCBuilder) {
        this.webClient = webClientCBuilder.baseUrl("https://api.openweathermap.org").build();
    }

    public Optional<WeatherDTO> getWeatherInfoByCommunityCode(String communityCode) {
        WeatherDTO weatherDTO = getLatLonByCommunityCode(communityCode)
            .flatMap(this::getWeatherInfoByCoordinates)
            .map(weatherFields -> new WeatherDTO(communityCode, weatherFields[0], weatherFields[1], weatherFields[2]))
            .block();

        return weatherDTO != null ? Optional.of(weatherDTO) : Optional.empty();
    }

    private Mono<Double[]> getLatLonByCommunityCode(String communityCode) {
        Optional<Community> community = communityRepository.findById(communityCode);
           
        if (community.isPresent()) {
            return webClient.get()
                    .uri("/geo/1.0/direct?q={capital},ES&appid={apiKey}", Capital.valueOfNombre(community.get().getName()), API_KEY)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(this::extractLatLon);

        } else {
            return Mono.empty();
        }
    }

    private Double[] extractLatLon(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode firstResult = jsonNode.get(0);
            double lat = firstResult.get("lat").asDouble();
            double lon = firstResult.get("lon").asDouble();
            return new Double[]{lat, lon};
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer coordenadas", e);
        }
    } 

    private Mono<String[]> getWeatherInfoByCoordinates(Double[] latLon) {
        double lat = latLon[0];
        double lon = latLon[1];

        return webClient.get()
                .uri("/data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}&units=metric", lat, lon, API_KEY)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::extractWeatherFields);
    }

    private String[] extractWeatherFields(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            JsonNode mainNode = jsonNode.get("main");

            String[] mainFields = new String[3];
            mainFields[0] = mainNode.get("temp").asText();
            mainFields[1] = mainNode.get("pressure").asText();
            mainFields[2] = mainNode.get("humidity").asText();

            return mainFields;
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer campos del clima", e);
        }
    }

}
