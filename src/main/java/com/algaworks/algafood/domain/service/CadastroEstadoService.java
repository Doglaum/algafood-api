package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public Estado atualizar(Long idEstado, Estado estado) {
        Estado estadoAtual = estadoRepository.buscar(idEstado);
        if (Objects.isNull(estadoAtual)) {
            throw new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrado", idEstado));
        }
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.salvar(estadoAtual);
    }

    public void excluir(Long idEstado) {
        if (cidadeRepository.existeCidadeCadastradaComEstadoInformado(idEstado)) {
            throw new EntidadeEmUsoException(String.format("O Estado de código %d não pode ser removido, pois está em uso", idEstado));
        }
        try {
            estadoRepository.remover(idEstado);
        } catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrado", idEstado));
        }
    }
}
