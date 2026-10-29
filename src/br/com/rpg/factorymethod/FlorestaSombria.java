package br.com.rpg.factorymethod;

import br.com.rpg.personagem.Inimigo;

public class FlorestaSombria extends Regiao {

    @Override
    protected Inimigo criarInimigo(int numero) {
        return new Lobo(numero);
    }

    @Override
    public String nome() {
        return "Floresta Sombria";
    }
}
