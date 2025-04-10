package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    /**
     * Persiste uma nova cidade no repositório.
     *
     * @param cidade a instância da cidade a ser adicionada.
     * @return a cidade persistida contendo seu ID gerado.
     */
    Cidade adicionar(final Cidade cidade);

    /**
     * Verifica se existem cidades persistidas associadas ao estado com o ID informado.
     *
     * @param idEstado o ID do estado a ser verificado.
     * @return true se existirem cidades associadas ao estado, caso contrário, false.
     */
    boolean existeCidadeCadastradaComEstadoInformado(final Long idEstado);

    /**
     * Lista todas as cidades registradas no repositório.
     *
     * @return uma lista contendo todas as instâncias de cidades disponíveis.
     */
    List<Cidade> listar();

    /**
     * Busca uma cidade no repositório com base no ID fornecido.
     *
     * @param idCidade o ID da cidade a ser buscada
     * @return a instância da cidade correspondente ao ID informado,
     *         ou null caso nenhuma cidade seja encontrada
     */
    Cidade buscar(final Long idCidade);

    /**
     * Remove uma cidade do repositório com base no ID informado.
     *
     * @param idCidade o ID da cidade a ser removida
     */
    void remover(final Long idCidade);
}
