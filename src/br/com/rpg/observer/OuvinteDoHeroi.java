package br.com.rpg.observer;

import br.com.rpg.personagem.Heroi;

/**
 * OBSERVER: quem quiser reagir quando a vida do herói muda implementa
 * esta interface e se inscreve no herói.
 */
@FunctionalInterface
public interface OuvinteDoHeroi {

    void vidaMudou(Heroi heroi, int vidaAnterior);
}
