package com.kuiko.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kuiko.api.model.Province;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

}
