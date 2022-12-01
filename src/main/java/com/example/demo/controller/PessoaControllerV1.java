package com.example.demo.controller;

import com.example.demo.model.PessoaModel;
import com.example.demo.service.PessoaServiceV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas/v1")
public class PessoaControllerV1 {

    @Autowired
    private PessoaServiceV1 service;

    @GetMapping("/{id}")
    public Optional<PessoaModel> findById(@PathVariable("id")long id){return service.findById(id);}

    @GetMapping
    public List<PessoaModel> findAll(){return  service.findALL(); }

    @PostMapping
    public PessoaModel save(@RequestBody PessoaModel model){
        return service.save(model);
    }

    @PutMapping
    public PessoaModel update(@RequestBody PessoaModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<PessoaModel> found = service.findById(id);
        if(found.isPresent()){
            service.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }

}
