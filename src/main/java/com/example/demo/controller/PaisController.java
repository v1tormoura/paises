package com.example.demo.controller;

import com.example.demo.model.PaisModel;
import com.example.demo.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paises/v1")
public class PaisController {

    @Autowired
    private PaisService service;

    @GetMapping()
    public List<PaisModel> findAll(){return service.findALL(); }

    @GetMapping("/{id}")
    public PaisModel findById(@PathVariable("id") long id){
        var PaisModel = service.findById(id);
        if(PaisModel.isPresent()){
            return PaisModel.get();
        } else{
            return null;
        }
    }
}
