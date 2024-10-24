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

import com.kuiko.api.model.Community;
import com.kuiko.api.model.CommunityDTO;
import com.kuiko.api.service.CommunityService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;


    @GetMapping
    public List<Community> getAllCommunities() {
        return communityService.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Community> getCommunityById(@PathVariable String code) {
        return communityService.findById(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Community createCommunity(@RequestBody Community community) {
        return communityService.save(community);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Community> updateCommunity(@PathVariable String code, @RequestBody Community community) {
        return communityService.findById(code)
                .map(existingCommunity -> {
                    existingCommunity.setName(community.getName());
                    Community updatedCommunity = communityService.save(existingCommunity);
                    return ResponseEntity.ok(updatedCommunity);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{code}")
    public ResponseEntity<Community> updateCommunity(@PathVariable String code, @RequestBody Map<String, Object> updates) {
        try {
            Community updatedCommunity = communityService.updateCommunity(code, updates);
            return ResponseEntity.ok(updatedCommunity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable String code) {
        if (communityService.findById(code).isPresent()) {
            communityService.deleteById(code);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ejercicio_3/{communityCode}")
    public ResponseEntity<?> getCommunityInfo(@PathVariable String communityCode) {
        Optional<CommunityDTO> communityInfo = communityService.getCommunityInfoByCommunityCode(communityCode);

        return communityInfo
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
