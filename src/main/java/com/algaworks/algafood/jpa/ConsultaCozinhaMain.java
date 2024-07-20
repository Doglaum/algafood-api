package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);
        CozinhaRepository bean = applicationContext.getBean(CozinhaRepository.class);

        for(Cozinha c: bean.listar()) {
            System.out.println(c.getNome());
        }

        Cozinha cozinha = new Cozinha("Brasileira");
        bean.salvar(cozinha);


        for(Cozinha c: bean.listar()) {
            System.out.println(c.getNome());
        }

        System.out.println("Bsucando " + bean.buscar(2L).getNome());


        System.out.println("Antes alteração: " + bean.buscar(1L).getNome());
        cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Teste");
        bean.salvar(cozinha);

        System.out.println("Depois alteração " + bean.buscar(1L).getNome());

        bean.remover(cozinha);

    }
}
