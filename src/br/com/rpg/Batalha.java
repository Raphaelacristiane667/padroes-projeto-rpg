package br.com.rpg;

import br.com.rpg.adapter.Acao;
import br.com.rpg.adapter.Controle;
import br.com.rpg.personagem.Heroi;
import br.com.rpg.personagem.Inimigo;
import br.com.rpg.singleton.DiarioDeBatalha;
import br.com.rpg.strategy.Agressivo;
import br.com.rpg.strategy.Defensivo;

import java.util.List;

/** Loop de batalha por turnos. Só conhece Controle, nunca o joystick. */
public class Batalha {

    private final Heroi heroi;
    private final Controle controle;
    private final DiarioDeBatalha diario = DiarioDeBatalha.getInstancia();

    public Batalha(Heroi heroi, Controle controle) {
        this.heroi = heroi;
        this.controle = controle;
    }

    /** Devolve true se o herói venceu a onda. */
    public boolean lutar(List<Inimigo> inimigos) {
        while (heroi.estaVivo() && inimigos.stream().anyMatch(Inimigo::estaVivo)) {
            Inimigo alvo = inimigos.stream().filter(Inimigo::estaVivo).findFirst().orElseThrow();
            Acao acao = controle.proximaAcao();
            switch (acao) {
                case ATACAR -> heroi.atacar(alvo);
                case FURIA -> {
                    heroi.setEstilo(new Agressivo());
                    heroi.atacar(alvo);
                }
                case DEFENDER -> heroi.setEstilo(new Defensivo());
                case BEBER_POCAO -> heroi.beberPocao(25);
            }
            for (Inimigo inimigo : inimigos) {
                if (inimigo.estaVivo() && heroi.estaVivo()) {
                    heroi.receberAtaque(inimigo);
                }
            }
            diario.proximoTurno();
        }
        boolean venceu = heroi.estaVivo();
        diario.registrar(venceu ? "Vitória!" : heroi.getNome() + " caiu em batalha...");
        return venceu;
    }
}
