package com.kuiko.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuiko.api.model.WeatherDTO;
import com.kuiko.api.service.CSVService;
import com.kuiko.api.service.OpenWeatherService;



@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private CSVService csvService;

    @Autowired
    private OpenWeatherService openWeatherService;


    @PostMapping("import/communities")
    public String importCommunities(@RequestParam String filePath) {
        try {
            csvService.importCommunities(filePath);
            return "Import completed";
        } catch (Exception e) {
            return "Error during import: " + e.getMessage();
        }
    }

    @PostMapping("import/provinces")
    public String importProvinces(@RequestParam String filePath) {
        try {
            csvService.importProvinces(filePath);
            return "Import completed";
        } catch (Exception e) {
            return "Error during import: " + e.getMessage();
        }
    }

    @GetMapping("/weather/{communityCode}")
    public ResponseEntity<WeatherDTO> getWeatherInfo(@PathVariable String communityCode) {
        Optional<WeatherDTO> weatherDTO = openWeatherService.getWeatherInfoByCommunityCode(communityCode);
        
        return weatherDTO
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}