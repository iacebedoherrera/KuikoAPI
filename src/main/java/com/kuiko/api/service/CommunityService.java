package com.kuiko.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiko.api.model.Community;
import com.kuiko.api.model.CommunityDTO;
import com.kuiko.api.repository.CommunityRepository;
import com.kuiko.api.repository.ProvinceRepository;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ProvinceRepository provinceRepository;


    public Optional<CommunityDTO> getCommunityInfoByCommunityCode(String communityCode) {
        Optional<Community> community = communityRepository.findById(communityCode);

        if (community.isPresent()) {
            int provinceCount = provinceRepository.countByCommunity_Code(communityCode);

            return Optional.of(new CommunityDTO(
                community.get().getCode(),
                community.get().getName(),
                provinceCount
            ));
        } else {
            return Optional.empty();
        }
    }

}
