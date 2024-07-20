package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        TypedQuery<Cozinha> fromCozinha = entityManager.createQuery("from Cozinha", Cozinha.class);
        return fromCozinha.getResultList();
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Transactional
    @Override
    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public void remover(Cozinha cozinha) {
        // criar uma instancia 'managed' pelo JPA
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }

}
