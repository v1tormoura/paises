package com.example.demo.service;

import com.example.demo.model.PessoaModel;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceV1 {

    @Autowired
    private PessoaRepository repository;

    public Optional<PessoaModel> findById(long id){return repository.findById(id);}

    public List<PessoaModel> findALL(){return repository.findAll();}

    public PessoaModel save(PessoaModel model){return repository.save(model);}

    public PessoaModel update(PessoaModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setName(model.getName());
            found.get().setGender(model.getGender());
            found.get().setCity(model.getCity());
            return repository.save(found.get());
        }
        else {
            return null;
        }

    }
    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }
}
