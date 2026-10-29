package br.com.rpg.decorator;

/**
 * DECORATOR: um encantamento embrulha uma arma e muda o dano e o nome dela.
 * Como o encantamento também É uma Arma, dá para colocar um por cima do
 * outro, em qualquer ordem, sem criar uma classe "EspadaDeFogoAfiada".
 */
public abstract class Encantamento implements Arma {

    protected final Arma arma;

    protected Encantamento(Arma arma) {
        this.arma = arma;
    }
}
