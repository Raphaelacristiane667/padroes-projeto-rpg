package br.com.rpg;

import br.com.rpg.adapter.Controle;
import br.com.rpg.adapter.JoystickAdapter;
import br.com.rpg.adapter.JoystickRetro;
import br.com.rpg.decorator.Arma;
import br.com.rpg.decorator.Lanca;
import br.com.rpg.decorator.Afiada;
import br.com.rpg.decorator.BencaoLendaria;
import br.com.rpg.decorator.RunaDeFogo;
import br.com.rpg.facade.NovaPartida;
import br.com.rpg.factorymethod.Regiao;
import br.com.rpg.personagem.Heroi;
import br.com.rpg.singleton.DiarioDeBatalha;
import br.com.rpg.strategy.Equilibrado;

import static br.com.rpg.adapter.JoystickRetro.BOTAO_A;
import static br.com.rpg.adapter.JoystickRetro.BOTAO_B;
import static br.com.rpg.adapter.JoystickRetro.BOTAO_X;
import static br.com.rpg.adapter.JoystickRetro.BOTAO_Y;

/** Uma mini aventura que passa pelos 7 padrões. */
public class Main {

    public static void main(String[] args) {
        titulo("FACADE: começando o jogo com uma chamada só");
        NovaPartida partida = new NovaPartida();
        Heroi heroi = partida.iniciar("Lyra");

        titulo("ADAPTER: o joystick antigo vira um Controle do jogo");
        JoystickRetro joystick = new JoystickRetro(BOTAO_A, BOTAO_A, BOTAO_B, BOTAO_X, BOTAO_Y, BOTAO_A);
        Controle controle = new JoystickAdapter(joystick);
        System.out.println("Sequência gravada: A, A, B, X, Y, A (repete)");

        titulo("FACTORY METHOD + STRATEGY + OBSERVER: Floresta Sombria");
        Regiao floresta = partida.getRegioes().get(0);
        Batalha batalha = new Batalha(heroi, controle);
        batalha.lutar(floresta.gerarOnda(2));

        titulo("DECORATOR: encantando uma arma nova");
        Arma lanca = new Lanca();
        System.out.println(lanca.nome() + " -> dano " + lanca.dano());
        lanca = new Afiada(lanca);
        System.out.println(lanca.nome() + " -> dano " + lanca.dano());
        lanca = new RunaDeFogo(lanca);
        System.out.println(lanca.nome() + " -> dano " + lanca.dano());
        lanca = new BencaoLendaria(lanca);
        System.out.println(lanca.nome() + " -> dano " + lanca.dano());
        Arma outraOrdem = new Afiada(new RunaDeFogo(new BencaoLendaria(new Lanca())));
        System.out.println("Mesmos encantamentos em outra ordem: " + outraOrdem.nome()
                + " -> dano " + outraOrdem.dano());
        heroi.equipar(lanca);
        heroi.beberPocao(60);

        titulo("FACTORY METHOD: outra região, outro inimigo");
        heroi.setEstilo(new Equilibrado());
        Regiao montanha = partida.getRegioes().get(1);
        batalha.lutar(montanha.gerarOnda(1));

        titulo("SINGLETON: o mesmo diário registrou tudo");
        DiarioDeBatalha diario = DiarioDeBatalha.getInstancia();
        System.out.println("Mesma instância? " + (diario == DiarioDeBatalha.getInstancia()));
        System.out.println("Eventos registrados na partida: " + diario.getRegistros().size());
    }

    private static void titulo(String texto) {
        System.out.println("\n===== " + texto + " =====");
    }
}
