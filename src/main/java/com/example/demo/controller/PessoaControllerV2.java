package com.example.demo.controller;

import com.example.demo.model.PessoaModel;
import com.example.demo.service.PessoaServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas/v2")
@Api(value = "pessoa Endpoint Version V2")
public class PessoaControllerV2 {

    @Autowired
    private PessoaServiceV2 service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Returns a pessoas by ID")
    public PessoaModel findById(
            @ApiParam(name = "id", value = "A valid integer value", required = true)
            @PathVariable("id") long id){
        //..........................................................
        var PessoaModel = service.findById(id) ;
        if(PessoaModel.isPresent()){
            buildEntityLink(PessoaModel.get());
            return PessoaModel.get();
        } else {
            return null;
        }
        //............................................................
    }

    @GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PagedModel<PessoaModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<PessoaModel> assembler
    ){
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<PessoaModel> pessoas = service.FindALL(pageable);

        for(PessoaModel client : pessoas){
            buildEntityLink(client);
        }

        return new ResponseEntity(assembler.toModel(pessoas), HttpStatus.OK);

    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public PessoaModel save(@RequestBody PessoaModel model){
        return service.save(model);
    }

    @PutMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
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

    @GetMapping("/find/email/{email}")
    public List<PessoaModel> findByEmail(@PathVariable("email") String email){
        return service.findByEmail(email);
    }

    private void buildEntityLink(PessoaModel model){
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(model.getId())
                ).withSelfRel()
        );

        if(!model.getPais().hasLinks()) {
            Link paisLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            PaisController.class).findById(model.getPais().getId())
            ).withSelfRel();
            model.getPais().add(paisLink);
        }
    }
}
