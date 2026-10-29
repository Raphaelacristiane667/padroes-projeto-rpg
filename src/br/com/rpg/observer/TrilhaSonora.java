package br.com.rpg.observer;

import br.com.rpg.personagem.Heroi;

/** Troca a música quando a vida cruza 30%, para cima ou para baixo. */
public class TrilhaSonora implements OuvinteDoHeroi {

    @Override
    public void vidaMudou(Heroi heroi, int vidaAnterior) {
        int limite = heroi.getVidaMaxima() * 30 / 100;
        if (vidaAnterior > limite && heroi.getVida() <= limite) {
            System.out.println("      [Música] tema de perigo começa a tocar");
        } else if (vidaAnterior <= limite && heroi.getVida() > limite) {
            System.out.println("      [Música] volta o tema de batalha");
        }
    }
}
