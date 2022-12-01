package com.example.demo.repository;


import com.example.demo.model.PessoaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

   Optional<PessoaModel> findById(long id);

   public Page<PessoaModel> findAll(Pageable pageable);

   List<PessoaModel> findByNameContainsIgnoreCaseOrderByName(String name);

    List<PessoaModel>
    findByEmailStartsWithIgnoreCase(String email);

}
