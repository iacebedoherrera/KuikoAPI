package com.kuiko.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuiko.api.model.Community;


@Repository
public interface CommunityRepository extends JpaRepository<Community, String> {
    
}
