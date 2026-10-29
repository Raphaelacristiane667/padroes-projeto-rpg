package br.com.rpg.decorator;

public class Espada implements Arma {

    @Override
    public int dano() {
        return 10;
    }

    @Override
    public String nome() {
        return "Espada";
    }
}
