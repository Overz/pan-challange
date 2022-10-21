package com.example.challange.repositories;

import com.example.challange.entities.SWResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface SwRepository extends JpaRepository<SWResultDTO, String> {
}
