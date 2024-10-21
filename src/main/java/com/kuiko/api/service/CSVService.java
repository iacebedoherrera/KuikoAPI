package com.kuiko.api.service;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiko.api.model.Community;
import com.kuiko.api.model.Province;
import com.kuiko.api.repository.CommunityRepository;
import com.kuiko.api.repository.ProvinceRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


@Service
public class CSVService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CommunityRepository communityRepository;


    public void importProvinces(String filePath) throws Exception {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build(); 
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .withCSVParser(parser)
                .build()) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                Province province = new Province();
                province.setCommunityCode(record[0]);
                province.setCode(Integer.parseInt(record[1]));
                province.setName(record[2]);

                Optional<Community> community = communityRepository.findById(record[0]);
                if (community.isPresent()) {
                    province.setCommunity(community.get());
                }

                provinceRepository.save(province);
            }
        }
    }

    public void importCommunities(String filePath) throws Exception {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build(); 
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withSkipLines(1)
                .withCSVParser(parser)
                .build()) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                Community community = new Community();
                community.setCode(record[0]);
                community.setName(record[1]);
                communityRepository.save(community);
            }
        }
    }

}
