package com.kuiko.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiko.api.model.Province;
import com.kuiko.api.repository.ProvinceRepository;



@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;


    public Optional<Province> getProvinceInfoByProvinceCode(int provinceCode) {
        return provinceRepository.findById(provinceCode);
    }

}
