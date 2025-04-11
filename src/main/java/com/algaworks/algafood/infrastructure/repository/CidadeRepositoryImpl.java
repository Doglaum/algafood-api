package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Cidade adicionar(final Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Override
    @Transactional
    public boolean existeCidadeCadastradaComEstadoInformado(Long idEstado) {
        String jpql = "SELECT COUNT(c) FROM Cidade c WHERE c.estado.id = :idEstado";
        Long count = entityManager.createQuery(jpql, Long.class).setParameter("idEstado", idEstado).getSingleResult();
        return count > 0;
    }

    @Override
    @Transactional
    public List<Cidade> listar() {
        return entityManager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    @Transactional
    public Cidade buscar(Long idCidade) {
        Cidade cidade = entityManager.find(Cidade.class, idCidade);
        if(Objects.isNull(cidade)) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade com id %d não encontrada", idCidade));
        }
        return entityManager.find(Cidade.class, idCidade);
    }

    @Override
    @Transactional
    public void remover(Long idCidade) {
        entityManager.remove(buscar(idCidade));
    }
}
