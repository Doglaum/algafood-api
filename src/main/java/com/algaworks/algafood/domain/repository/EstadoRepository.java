package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    /**
     * Busca a lista de Estado cadastradas no sistema
     *
     * @return uma lista contendo os estados
     */
    List<Estado> listar();

    /**
     * Busca uma instância de Estado no repositório com base no ID fornecido.
     *
     * @param id o ID do estado a ser buscado
     * @return a instância do estado correspondente ao ID informado,
     *         ou null caso nenhuma instância seja encontrada
     */
    Estado buscar(Long id);

    /**
     * Persiste uma instância de Estado no repository
     *
     * @param estado o objeto Estado a ser persistido
     * @return o objeto persistido com seu ID
     */
    Estado salvar(Estado estado);

    /**
     * Remove uma instância de Estado do repositório com base no ID fornecido.
     *
     * @param estado o ID do estado a ser removido
     */
    void remover(Long estado);
}
