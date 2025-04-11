package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long idEstado, Estado estado) {
        Estado estadoAtual = estadoRepository.findById(idEstado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Estado com id %d não encontrado", idEstado))
                );
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    public void excluir(Long idEstado) {
        try {
            estadoRepository.deleteById(idEstado);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi encontrada estado com id %d", idEstado));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", idEstado));
        }
    }
}
