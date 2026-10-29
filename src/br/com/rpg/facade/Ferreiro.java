package br.com.rpg.facade;

import br.com.rpg.decorator.Arma;
import br.com.rpg.decorator.Espada;

class Ferreiro {

    Arma armaInicial() {
        Arma arma = new Espada();
        System.out.println("  Ferreiro entregou: " + arma.nome());
        return arma;
    }
}
