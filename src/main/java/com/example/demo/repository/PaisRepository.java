package com.example.demo.repository;

import com.example.demo.model.PaisModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<PaisModel, Long> {


    Optional<PaisModel> findById(long id);
}
