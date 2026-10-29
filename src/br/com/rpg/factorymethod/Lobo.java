package br.com.rpg.factorymethod;

import br.com.rpg.personagem.Inimigo;

public class Lobo extends Inimigo {

    public Lobo(int numero) {
        super("Lobo " + numero, 18);
    }

    @Override
    public int ataque() {
        return 6;
    }

    @Override
    public String gritoDeGuerra() {
        return "Auuuuu!";
    }
}
