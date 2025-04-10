package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CozinhaRepository {

    /**
     * Retorna uma lista contendo todas as instâncias de Cozinha disponíveis no repositório.
     *
     * @return uma lista de objetos Cozinha.
     */
    List<Cozinha> listar();

    /**
     * Persiste uma nova instância de Cozinha no repositório.
     *
     **/
    Cozinha salvar(Cozinha cozinha);

    /**
     * Busca uma instância de Cozinha no repositório com base no ID fornecido.
     *
     * @param id o ID da cozinha a ser buscada
     * @return a instância da cozinha correspondente ao ID informado
     */
    Cozinha buscar(Long id);

    /**
     * Remove uma instância do repositório com base no ID fornecido.
     *
     * @param id o ID da entidade a ser removida
     */
    void remover(Long id);

}
