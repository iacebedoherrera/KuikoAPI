package com.kuiko.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiko.api.model.Province;
import com.kuiko.api.model.ProvinceDTO;
import com.kuiko.api.repository.ProvinceRepository;



@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;


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
