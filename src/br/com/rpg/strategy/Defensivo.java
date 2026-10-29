package br.com.rpg.strategy;

/** Bate com metade da força, mas só recebe metade do dano. */
public class Defensivo implements EstiloDeCombate {

    @Override
    public int danoCausado(int danoDaArma) {
        return danoDaArma / 2;
    }

    @Override
    public int danoRecebido(int danoDoInimigo) {
        return danoDoInimigo / 2;
    }

    @Override
    public String nome() {
        return "defensivo";
    }
}
