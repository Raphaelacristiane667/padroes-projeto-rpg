package br.com.rpg.strategy;

public class Equilibrado implements EstiloDeCombate {

    @Override
    public int danoCausado(int danoDaArma) {
        return danoDaArma;
    }

    @Override
    public int danoRecebido(int danoDoInimigo) {
        return danoDoInimigo;
    }

    @Override
    public String nome() {
        return "equilibrado";
    }
}
