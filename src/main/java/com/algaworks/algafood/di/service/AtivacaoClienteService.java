package com.algaworks.algafood.di.service;


import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    //Inejetar 1
    @Autowired
    private Notificador notificador;

    //Injetar 2
    @Autowired
    public AtivacaoClienteService (Notificador notificador) {
        this.notificador = notificador;
    }

    //Injetar 3
    @Autowired
    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        notificador.notificar(cliente, "Seu cadsatro no sistema está ativo");
    }
}
