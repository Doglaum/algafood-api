package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        TypedQuery<Restaurante> fromRestaurante = entityManager.createQuery("from Restaurante", Restaurante.class);
        return fromRestaurante.getResultList();
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return entityManager.merge(restaurante);
    }

    @Transactional
    @Override
    public Restaurante buscar(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        // criar uma instancia 'managed' pelo JPA
        restaurante = buscar(restaurante.getId());
        entityManager.remove(restaurante);
    }
}
