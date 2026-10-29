package br.com.rpg.factorymethod;

import br.com.rpg.personagem.Inimigo;

public class MontanhaGelada extends Regiao {

    @Override
    protected Inimigo criarInimigo(int numero) {
        return new GolemDeGelo(numero);
    }

    @Override
    public String nome() {
        return "Montanha Gelada";
    }
}
