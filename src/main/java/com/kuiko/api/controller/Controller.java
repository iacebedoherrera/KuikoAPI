package com.kuiko.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuiko.api.model.Province;
import com.kuiko.api.model.ProvinceDTO;
import com.kuiko.api.service.CSVService;
import com.kuiko.api.service.ProvinceService;



@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CSVService csvService;


    @GetMapping("import/communities")
    public String importCommunities(@RequestParam String filePath) {
        try {
            csvService.importCommunities(filePath);
            return "Import completed";
        } catch (Exception e) {
            return "Error during import: " + e.getMessage();
        }
    }

    @GetMapping("import/provinces")
    public String importProvinces(@RequestParam String filePath) {
        try {
            csvService.importProvinces(filePath);
            return "Import completed";
        } catch (Exception e) {
            return "Error during import: " + e.getMessage();
        }
    }

    @GetMapping("/province/{provinceCode}")
    public ResponseEntity<?> getProvinceInfo(@PathVariable int provinceCode) {
        Optional<Province> provinceInfo = provinceService.getProvinceInfoByProvinceCode(provinceCode);

        return provinceInfo.map(province -> {
            ProvinceDTO response = new ProvinceDTO(
                province.getCommunity().getCode(),
                province.getCommunity().getName(),
                province.getCode(),
                province.getName()
            );
            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}