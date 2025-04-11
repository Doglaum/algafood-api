package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
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
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade adicionar(final Cidade cidade) {
        if (cidade.getEstado() != null) {
            cidade.setEstado(getEstado(cidade));
        }
        return cidadeRepository.adicionar(cidade);
    }

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    public Cidade atualizar(Long idCidade, Cidade cidade) {
        try {
            final Cidade cidadeAtual = cidadeRepository.buscar(idCidade);
            if (Objects.nonNull(cidade.getEstado())) {
                cidade.setEstado(getEstado(cidade));
            }
            BeanUtils.copyProperties(cidade, cidadeAtual, "id");
            return cidadeRepository.adicionar(cidadeAtual);
        } catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d não encontrada", idCidade));
        }
    }

    private Estado getEstado(Cidade cidade) {
        final Long idEstado = cidade.getEstado().getId();
        final Estado estado = estadoRepository.buscar(idEstado);
        if (Objects.isNull(estado)) {
            throw new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrada", cidade.getEstado().getId()));
        }
        return estado;
    }

    public void deletar(Long idCidade) {
        try {
            cidadeRepository.remover(idCidade);
        } catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d não encontrada", idCidade));
        }
    }
}
