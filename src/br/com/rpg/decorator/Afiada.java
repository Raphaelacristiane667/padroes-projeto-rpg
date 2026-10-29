package br.com.rpg.decorator;

public class Afiada extends Encantamento {

    public Afiada(Arma arma) {
        super(arma);
    }

    @Override
    public int dano() {
        return arma.dano() + 3;
    }

    @Override
    public String nome() {
        return arma.nome() + " afiada";
    }
}
