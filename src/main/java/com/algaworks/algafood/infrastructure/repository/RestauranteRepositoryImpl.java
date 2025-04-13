package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var jpql = new StringBuilder();
        jpql.append("SELECT r FROM Restaurante r where 1 = 1");
        var parametros = new HashMap<String, Object>();

        if(StringUtils.hasLength(nome)){
            jpql.append(" and r.nome like :nome");
            parametros.put("nome", "%" + nome + "%");
        }
        if(null != taxaFreteInicial){
            jpql.append(" and r.taxaFrete >= :taxaFreteInicial");
            parametros.put("taxaFreteInicial", taxaFreteInicial);
        }
        if(null != taxaFreteFinal){
            jpql.append(" and r.taxaFrete <= :taxaFreteFinal");
            parametros.put("taxaFreteFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
        parametros.forEach(query::setParameter);
        return query.getResultList();
    }
}
