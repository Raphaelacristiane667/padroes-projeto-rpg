package br.com.rpg.strategy;

/** Bate 50% mais forte, mas também apanha 50% mais. */
public class Agressivo implements EstiloDeCombate {

    @Override
    public int danoCausado(int danoDaArma) {
        return danoDaArma * 3 / 2;
    }

    @Override
    public int danoRecebido(int danoDoInimigo) {
        return danoDoInimigo * 3 / 2;
    }

    @Override
    public String nome() {
        return "agressivo";
    }
}
