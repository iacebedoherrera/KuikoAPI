package com.kuiko.api.service;

import java.util.Optional;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuiko.api.exception.CommunityParamsNotValidException;
import com.kuiko.api.model.Community;
import com.kuiko.api.model.CommunityDTO;
import com.kuiko.api.repository.CommunityRepository;
import com.kuiko.api.repository.ProvinceRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private ProvinceRepository provinceRepository;


    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    public Optional<Community> findById(String code) {
        return communityRepository.findById(code);
    }

    public Community save(Community community) {
        if (community.getCode().isEmpty()) {
            throw new CommunityParamsNotValidException("ERROR. El código de comunidad autónoma está vacío.");
        }
        if (community.getName().isEmpty()) {
            throw new CommunityParamsNotValidException("ERROR. El nombre de comunidad autónoma está vacío.");
        }
        return communityRepository.save(community);
    }

    public Community updateCommunity(String code, Map<String, Object> updates) {
        Community community = communityRepository.findById(code)
                .orElseThrow(() -> new EntityNotFoundException("Community not found"));

        if (updates.containsKey("name")) {
            community.setName((String) updates.get("name"));
            community = save(community);
        } else {
            throw new CommunityParamsNotValidException("ERROR. Ese campo no es modificable.");
        }

        return community;
    }

    public void deleteById(String code) {
        communityRepository.deleteById(code);
    }

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
