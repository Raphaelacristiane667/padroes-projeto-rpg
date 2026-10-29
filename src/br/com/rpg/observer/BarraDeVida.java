package br.com.rpg.observer;

import br.com.rpg.personagem.Heroi;

/** Desenha a barra de vida na tela toda vez que a vida muda. */
public class BarraDeVida implements OuvinteDoHeroi {

    private static final int TAMANHO = 20;

    @Override
    public void vidaMudou(Heroi heroi, int vidaAnterior) {
        int cheios = heroi.getVida() * TAMANHO / heroi.getVidaMaxima();
        String barra = "#".repeat(cheios) + "-".repeat(TAMANHO - cheios);
        System.out.println("      HP [" + barra + "] " + heroi.getVida() + "/" + heroi.getVidaMaxima());
    }
}
