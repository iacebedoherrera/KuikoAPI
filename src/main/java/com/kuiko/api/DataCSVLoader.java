package com.kuiko.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.kuiko.api.service.CSVService;

@Component
public class DataCSVLoader implements ApplicationRunner {

    @Autowired
    private CSVService CSVService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        CSVService.importCommunities("src/main/resources/static/COMUNIDAD_AUTONOMA.csv");
        
        CSVService.importProvinces("src/main/resources/static/PROVINCIA.csv");
    }

}
