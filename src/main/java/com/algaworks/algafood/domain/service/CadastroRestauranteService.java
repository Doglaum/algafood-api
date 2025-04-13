package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(final Restaurante restaurante) {
        final Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(final Long idRestaurante) {
        return restauranteRepository.findById(idRestaurante).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format("Não existe restaurante com id %d", idRestaurante)));
    }

    public Restaurante atualizar(final Long idRestaurante, Restaurante restaurante) {
        final Restaurante restauranteAtual = buscar(idRestaurante);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        if (Objects.nonNull(restaurante.getCozinha())) {
            Long cozinhaId = restaurante.getCozinha().getId();
            Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(
                    () -> new EntidadeNaoEncontradaException(String.format("Não existe cozinha com id %d", cozinhaId))
            );
            restauranteAtual.setCozinha(cozinha);
        }
        return restauranteRepository.save(restauranteAtual);
    }

    public Restaurante atualizarParcial(final Long idRestaurante, Map<String, Object> campos) {
        Restaurante restauranteAtual = buscar(idRestaurante);
        restauranteAtual.setId(idRestaurante);
        merge(campos, restauranteAtual);
        return atualizar(idRestaurante, restauranteAtual);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            if (Objects.nonNull(field)) {
                ReflectionUtils.makeAccessible(field);
                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            }
        });
    }

    public List<Restaurante> findByTaxaFrenteBetween(BigDecimal min, BigDecimal max) {
        return restauranteRepository.findByTaxaFreteBetween(min, max);
    }

    public List<Restaurante> findByNomeECozinha(String nome, Long cozinhaId) {
        return restauranteRepository.consultarPorNomeECozinhaOrm(nome, cozinhaId);
    }

    public List<Restaurante> findByNomeAndTaxaFreteInicialAndTaxaFreteFinal(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.find(
                nome,
                taxaInicial,
                taxaFinal);
    }

}