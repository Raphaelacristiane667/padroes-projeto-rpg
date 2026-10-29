package br.com.rpg.factorymethod;

import br.com.rpg.personagem.Inimigo;

public class GolemDeGelo extends Inimigo {

    public GolemDeGelo(int numero) {
        super("Golem de Gelo " + numero, 40);
    }

    @Override
    public int ataque() {
        return 12;
    }

    @Override
    public String gritoDeGuerra() {
        return "*estalos de gelo*";
    }
}
