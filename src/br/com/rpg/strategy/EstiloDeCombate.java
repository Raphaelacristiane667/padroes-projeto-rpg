package br.com.rpg.strategy;

/**
 * STRATEGY: cada estilo calcula de um jeito diferente o dano causado
 * e o dano recebido. O herói pode trocar de estilo no meio da luta.
 */
public interface EstiloDeCombate {

    int danoCausado(int danoDaArma);

    int danoRecebido(int danoDoInimigo);

    String nome();
}
