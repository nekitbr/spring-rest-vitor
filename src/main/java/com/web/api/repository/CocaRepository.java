package com.web.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.api.model.Coca;

@Repository
public interface CocaRepository extends JpaRepository<Coca, Long>{}
