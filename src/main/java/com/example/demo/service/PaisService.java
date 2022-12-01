package com.example.demo.service;

import com.example.demo.model.PaisModel;
import com.example.demo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    public List<PaisModel> findALL(){return repository.findAll();}

    public Optional<PaisModel> findById(long id){return repository.findById(id);}
}
