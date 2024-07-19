package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);
        CadastroCozinha bean = applicationContext.getBean(CadastroCozinha.class);

        for(Cozinha c: bean.listar()) {
            System.out.println(c.getNome());
        }

        Cozinha cozinha = new Cozinha("Brasileira");
        bean.adicionar(cozinha);


        for(Cozinha c: bean.listar()) {
            System.out.println(c.getNome());
        }

    }
}
