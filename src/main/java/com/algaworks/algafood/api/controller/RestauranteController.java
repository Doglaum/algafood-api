package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.algaworks.algafood.infrastructure.repository.spec.RestaurantesSpecs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantesSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestaurantesSpecs.comNomeSemelhante;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping()
    public List<Restaurante> listar() {
        return cadastroRestauranteService.listar();
    }

    @GetMapping(value = "/{idRestaurante}")
    public Restaurante buscar(
            @PathVariable Long idRestaurante) {
        return cadastroRestauranteService.buscar(idRestaurante);
    }

    @PostMapping
    public ResponseEntity<?> adicionar(
            @RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping(value = "/{idRestaurante}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizar(
            @PathVariable Long idRestaurante,
            @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = cadastroRestauranteService.buscar(idRestaurante);
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
            restaurante = cadastroRestauranteService.salvar(restauranteAtual);
            return ResponseEntity.ok(restauranteAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{idRestaurante}")
    public ResponseEntity<?> atualizarParcial(
            @PathVariable Long idRestaurante,
            @RequestBody Map<String, Object> campos) {
        try {
            Restaurante restauranteAtual = cadastroRestauranteService.atualizarParcial(idRestaurante, campos);
            return ResponseEntity.ok(restauranteAtual);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/por-taxa-frete")
    public ResponseEntity<List<Restaurante>> restaurantesPorTaxaFrete(
            @RequestParam(value = "minimo") BigDecimal min,
            @RequestParam(value = "maximo") BigDecimal max) {
        List<Restaurante> byTaxaFrete = cadastroRestauranteService.findByTaxaFreteBetween(min, max);
        return ResponseEntity.ok(byTaxaFrete);
    }

    @GetMapping(value = "/por-nome-e-cozinha")
    public ResponseEntity<List<Restaurante>> restaurantesPorNomeECozinha(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "cozinhaId") Long cozinhaId) {
        return ResponseEntity.ok(cadastroRestauranteService.findByNomeECozinha(nome, cozinhaId));
    }

    @GetMapping(value = "/por-parametros")
    public ResponseEntity<List<Restaurante>> findPorParametros(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "taxaInicial", required = false) BigDecimal taxaInicial,
            @RequestParam(value = "taxaFinal", required = false) BigDecimal taxaFinal) {
        return ResponseEntity.ok(cadastroRestauranteService.findByNomeAndTaxaFreteInicialAndTaxaFreteFinal(nome, taxaInicial, taxaFinal));
    }

    @GetMapping(value = "/por-frete-gratis")
    public ResponseEntity<List<Restaurante>> porFreteGratis(
            @RequestParam(value = "nome") String nome) {
        return ResponseEntity.ok(cadastroRestauranteService.findComFreteGratis(nome));
    }

}
