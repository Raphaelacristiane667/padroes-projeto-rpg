package br.com.rpg.decorator;

public class RunaDeFogo extends Encantamento {

    public RunaDeFogo(Arma arma) {
        super(arma);
    }

    @Override
    public int dano() {
        return arma.dano() + 6;
    }

    @Override
    public String nome() {
        return arma.nome() + " de fogo";
    }
}
