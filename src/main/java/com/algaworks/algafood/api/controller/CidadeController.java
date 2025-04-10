package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidadeService.adicionar(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.ok().body(cadastroCidadeService.listar());
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@PathVariable Long idCidade, @RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidadeService.atualizar(idCidade, cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().body(cidade);
    }

    @DeleteMapping(value = "/{idCidade}")
    public ResponseEntity deletar(@PathVariable Long idCidade) {
        try {
            cadastroCidadeService.deletar(idCidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }

}
