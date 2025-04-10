package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public List<Estado> listar() {
        return cadastroEstadoService.listar();
    }


    @PostMapping
    public ResponseEntity<Estado> cadastrar(@RequestBody Estado estado) {
        estado = cadastroEstadoService.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @PutMapping(value = "/{idEstado}")
    public ResponseEntity<?> atualizar(@PathVariable Long idEstado, @RequestBody Estado estado) {
        try {
            estado = cadastroEstadoService.atualizar(idEstado, estado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(estado);
    }

    @DeleteMapping(value = "/{idEstado}")
    public ResponseEntity deletar(@PathVariable Long idEstado) {
        try {
            cadastroEstadoService.excluir(idEstado);
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.noContent().build();

    }

}
