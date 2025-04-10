package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public Cidade buscar(Long idCidade) {
        return entityManager.find(Cidade.class, idCidade);
    }

    @Override
    public void remover(Long idCidade) {
        entityManager.remove(buscar(idCidade));
    }
}
