package br.com.rpg.facade;

import br.com.rpg.factorymethod.FlorestaSombria;
import br.com.rpg.factorymethod.MontanhaGelada;
import br.com.rpg.factorymethod.Regiao;

import java.util.List;

class CarregadorDeMapa {

    List<Regiao> carregar() {
        System.out.println("  Mapa carregado: 2 regiões");
        return List.of(new FlorestaSombria(), new MontanhaGelada());
    }
}
