package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade adicionar(final Cidade cidade) {
        validarEstado(cidade);
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade atualizar(Long idCidade, Cidade cidade) {
        final Cidade cidadeAtual = cidadeRepository.findById(idCidade)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cidade com id %d não encontrada", idCidade)));
        if (Objects.nonNull(cidade.getEstado())) {
            validarEstado(cidade);
        }
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");
        return cidadeRepository.save(cidadeAtual);
    }

    private void validarEstado(Cidade cidade) {
        final Long idEstado = cidade.getEstado().getId();
        final Estado estado = estadoRepository.findById(idEstado).orElseThrow(
                () -> new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrado", idEstado))
        );
        cidade.setEstado(estado);
    }

    public void deletar(Long idCidade) {
        final Cidade cidade = cidadeRepository.findById(idCidade)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cidade com id %d não encontrada", idCidade)));
        cidadeRepository.delete(cidade);
    }
}
