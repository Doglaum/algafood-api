package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        TypedQuery<Estado> fromCozinha = entityManager.createQuery("from Estado", Estado.class);
        return fromCozinha.getResultList();
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return entityManager.merge(estado);
    }

    @Transactional
    @Override
    public Estado buscar(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public void remover(Estado estado) {
        // criar uma instancia 'managed' pelo JPA
        estado = buscar(estado.getId());
        entityManager.remove(estado);
    }
}
