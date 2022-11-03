package com.example.challange.repositories;

import com.example.challange.entities.SwBaseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SwRepository extends JpaRepository<SwBaseDto, String> {}
