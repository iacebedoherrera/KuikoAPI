package com.kuiko.api.service;

import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiko.api.exception.CommunityNotFoundException;
import com.kuiko.api.exception.ProvinceParamsNotValidException;
import com.kuiko.api.model.Community;
import com.kuiko.api.model.Province;
import com.kuiko.api.model.ProvinceDTO;
import com.kuiko.api.repository.CommunityRepository;
import com.kuiko.api.repository.ProvinceRepository;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CommunityRepository communityRepository;


    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    public Optional<Province> findById(int code) {
        return provinceRepository.findById(code);
    }

    public Province save(Province province) {
        if (province.getCode() == null) {
            throw new ProvinceParamsNotValidException("ERROR. El código de provincia es nulo.");
        }
        if (province.getName().isEmpty()) {
            throw new ProvinceParamsNotValidException("ERROR. El nombre de la provincia está vacío.");
        }
        Optional<Community> community = communityRepository.findById(province.getCommunity().getCode());
        if (community.isEmpty()) {
            throw new CommunityNotFoundException("ERROR. No se encontró la comunidad autónoma con código: " + province.getCommunity().getCode());
        }
        return provinceRepository.save(province);
    }

    public Province updateProvince(int code, Map<String, Object> updates) {
        Province province = provinceRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));

        if (updates.containsKey("name")) {
            province.setName((String) updates.get("name"));
            province = save(province);
        } else {
            throw new ProvinceParamsNotValidException("ERROR. Ese campo no es modificable.");
        }

        return province;
    }

    public void deleteById(int code) {
        provinceRepository.deleteById(code);
    }

    public Optional<ProvinceDTO> getProvinceInfoByProvinceCode(int provinceCode) {
        Optional<Province> province = provinceRepository.findById(provinceCode);

        if (province.isPresent()) {
            return Optional.of(new ProvinceDTO(
                province.get().getCommunity().getCode(),
                province.get().getCommunity().getName(),
                province.get().getCode(),
                province.get().getName()
            ));
        } else {
            return Optional.empty();
        }
    }

}
