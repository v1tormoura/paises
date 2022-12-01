package com.example.demo.service;

import com.example.demo.model.PessoaModel;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceV2 {
    @Autowired
    private PessoaRepository repository;

    public Optional<PessoaModel> findById(long id) {return repository.findById(id);}

    public Page<PessoaModel> FindALL(Pageable pageable){return repository.findAll(pageable);}

    public PessoaModel save(PessoaModel model){return repository.save(model);}

    public PessoaModel update(PessoaModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setName(model.getName());
            found.get().setGender(model.getGender());
            found.get().setCity(model.getCity());
            //..new attribute of V2 - email
            found.get().setEmail(model.getEmail());
            return repository.save(found.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }
    public List<PessoaModel> findByEmail(String email){
        return repository.findByEmailStartsWithIgnoreCase(email);
    }
}
