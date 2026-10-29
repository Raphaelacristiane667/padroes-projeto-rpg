package br.com.rpg.decorator;

public class Lanca implements Arma {

    @Override
    public int dano() {
        return 7;
    }

    @Override
    public String nome() {
        return "Lança";
    }
}
