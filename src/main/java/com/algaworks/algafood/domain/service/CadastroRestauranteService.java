package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(final Restaurante restaurante) {
        final Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (Objects.isNull(cozinha)) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(final Long idRestaurante) {
        return restauranteRepository.buscar(idRestaurante);
    }

    public Restaurante atualizar(Long idRestaurante, Restaurante restaurante) {
        final Restaurante restauranteAtual = restauranteRepository.buscar(idRestaurante);
        if (Objects.isNull(restauranteAtual)) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe restaurante com id %d", idRestaurante));
        }
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        if (Objects.nonNull(restaurante.getCozinha())) {
            final Cozinha cozinha = restaurante.getCozinha();
            Cozinha cozinhaAtual = cozinhaRepository.buscar(restauranteAtual.getCozinha().getId());
            if (Objects.isNull(cozinhaAtual)) {
                throw new EntidadeNaoEncontradaException(
                        String.format("Não existe cozinha com id %d", cozinha.getId()));
            }
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            restauranteAtual.setCozinha(cozinhaAtual);
        }
        return restauranteRepository.salvar(restauranteAtual);
    }

}
