package com.algaworks.algafood.jpa;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listar() {
        TypedQuery<Cozinha> fromCozinha = entityManager.createQuery("from Cozinha", Cozinha.class);
        return fromCozinha.getResultList();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Transactional
    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    public void remover(Cozinha cozinha) {
        // criar uma instancia 'managed' pelo JPA
        cozinha = buscar(cozinha.getId());
        entityManager.remove(cozinha);
    }
}
