package com.kuiko.api.controller;

import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuiko.api.model.Province;
import com.kuiko.api.model.ProvinceDTO;
import com.kuiko.api.service.ProvinceService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;


    @GetMapping
    public List<Province> getAllProvinces() {
        return provinceService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Province> getProvinceById(@PathVariable Integer code) {
        return provinceService.findById(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Province createProvince(@RequestBody Province province) {
        return provinceService.save(province);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Province> updateProvince(@PathVariable Integer code, @RequestBody Province province) {
        return provinceService.findById(code)
                .map(existingProvince -> {
                    existingProvince.setName(province.getName());
                    existingProvince.setCommunity(province.getCommunity());
                    Province updatedProvince = provinceService.save(existingProvince);
                    return ResponseEntity.ok(updatedProvince);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{code}")
    public ResponseEntity<Province> updateProvince(@PathVariable int code, @RequestBody Map<String, Object> updates) {
        try {
            Province updatedProvince = provinceService.updateProvince(code, updates);
            return ResponseEntity.ok(updatedProvince);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProvince(@PathVariable Integer code) {
        if (provinceService.findById(code).isPresent()) {
            provinceService.deleteById(code);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ejercicio_2/{provinceCode}")
    public ResponseEntity<?> getProvinceInfo(@PathVariable int provinceCode) {
        Optional<ProvinceDTO> provinceInfo = provinceService.getProvinceInfoByProvinceCode(provinceCode);

        return provinceInfo
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
