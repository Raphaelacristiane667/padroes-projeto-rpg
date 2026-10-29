package br.com.rpg.facade;

import br.com.rpg.factorymethod.Regiao;
import br.com.rpg.observer.BarraDeVida;
import br.com.rpg.observer.Conquistas;
import br.com.rpg.observer.TrilhaSonora;
import br.com.rpg.personagem.Heroi;

import java.util.List;

/**
 * FACADE: começar um jogo envolve áudio, mapa, save, arma inicial e
 * inscrever a interface nos eventos do herói. Quem chama só faz
 * NovaPartida.iniciar("nome") e recebe tudo pronto.
 *
 * Os subsistemas são package-private: de fora deste pacote, o único
 * caminho é pela fachada.
 */
public class NovaPartida {

    private final SistemaDeAudio audio = new SistemaDeAudio();
    private final CarregadorDeMapa mapa = new CarregadorDeMapa();
    private final SalvamentoAutomatico save = new SalvamentoAutomatico();
    private final Ferreiro ferreiro = new Ferreiro();

    private List<Regiao> regioes;

    public Heroi iniciar(String nomeHeroi) {
        System.out.println("Preparando nova partida...");
        audio.tocarAbertura();
        regioes = mapa.carregar();
        save.criarSlot(nomeHeroi);
        Heroi heroi = new Heroi(nomeHeroi, 60, ferreiro.armaInicial());
        heroi.inscrever(new BarraDeVida());
        heroi.inscrever(new TrilhaSonora());
        heroi.inscrever(new Conquistas());
        System.out.println("Pronto! Boa sorte, " + nomeHeroi + ".");
        return heroi;
    }

    public List<Regiao> getRegioes() {
        return regioes;
    }
}
