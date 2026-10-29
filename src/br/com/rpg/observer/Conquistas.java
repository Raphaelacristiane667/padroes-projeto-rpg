package br.com.rpg.observer;

import br.com.rpg.personagem.Heroi;

import java.util.LinkedHashSet;
import java.util.Set;

/** Desbloqueia conquistas a partir das mudanças de vida. */
public class Conquistas implements OuvinteDoHeroi {

    private final Set<String> desbloqueadas = new LinkedHashSet<>();

    @Override
    public void vidaMudou(Heroi heroi, int vidaAnterior) {
        if (heroi.getVida() > 0 && heroi.getVida() <= 5) {
            desbloquear("Por um fio: sobreviveu com 5 de vida ou menos");
        }
        if (heroi.getVida() == heroi.getVidaMaxima() && vidaAnterior < heroi.getVidaMaxima()) {
            desbloquear("Renovado: voltou à vida cheia");
        }
    }

    private void desbloquear(String conquista) {
        if (desbloqueadas.add(conquista)) {
            System.out.println("      [Conquista] " + conquista);
        }
    }

    public Set<String> getDesbloqueadas() {
        return desbloqueadas;
    }
}
