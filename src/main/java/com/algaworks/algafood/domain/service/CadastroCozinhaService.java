package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaJaExisteException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(final Cozinha cozinha) {
        if(cozinhaRepository.existsByNomeIgnoreCase(cozinha.getNome())) {
            throw new CozinhaJaExisteException(String.format("Já existe uma cozinha com o nome '%s'.", cozinha.getNome()));
        }
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(final Long cozinhaId) {
        try {
            cozinhaRepository.deleteById(cozinhaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi encontrada a cozinha com id %d", cozinhaId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }

    public Cozinha buscar(final Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format("Não foi encontrada a cozinha com id %d", cozinhaId))
        );
    }

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }
}
