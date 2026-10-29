package br.com.rpg.personagem;

import br.com.rpg.decorator.Arma;
import br.com.rpg.observer.OuvinteDoHeroi;
import br.com.rpg.singleton.DiarioDeBatalha;
import br.com.rpg.strategy.EstiloDeCombate;
import br.com.rpg.strategy.Equilibrado;

import java.util.ArrayList;
import java.util.List;

/**
 * O herói junta três padrões: usa uma Arma (que pode estar decorada),
 * luta com um EstiloDeCombate (Strategy) e avisa ouvintes quando
 * a vida muda (é o "sujeito" do Observer).
 */
public class Heroi {

    private final String nome;
    private final int vidaMaxima;
    private int vida;
    private Arma arma;
    private EstiloDeCombate estilo = new Equilibrado();
    private final List<OuvinteDoHeroi> ouvintes = new ArrayList<>();

    public Heroi(String nome, int vidaMaxima, Arma arma) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.arma = arma;
    }

    // ---- Observer ----

    public void inscrever(OuvinteDoHeroi ouvinte) {
        ouvintes.add(ouvinte);
    }

    public void desinscrever(OuvinteDoHeroi ouvinte) {
        ouvintes.remove(ouvinte);
    }

    private void mudarVida(int novaVida) {
        int anterior = vida;
        vida = Math.max(0, Math.min(vidaMaxima, novaVida));
        if (vida != anterior) {
            ouvintes.forEach(o -> o.vidaMudou(this, anterior));
        }
    }

    // ---- Combate ----

    public void atacar(Inimigo alvo) {
        int dano = estilo.danoCausado(arma.dano());
        alvo.receberDano(dano);
        DiarioDeBatalha.getInstancia().registrar(nome + " (" + estilo.nome() + ") ataca "
                + alvo.getNome() + " com " + arma.nome() + ": " + dano + " de dano"
                + (alvo.estaVivo() ? "" : ". " + alvo.getNome() + " foi derrotado!"));
    }

    public void receberAtaque(Inimigo atacante) {
        int dano = estilo.danoRecebido(atacante.ataque());
        DiarioDeBatalha.getInstancia().registrar(atacante.getNome() + " ataca "
                + nome + ": " + dano + " de dano");
        mudarVida(vida - dano);
    }

    public void beberPocao(int cura) {
        DiarioDeBatalha.getInstancia().registrar(nome + " bebe uma poção (+" + cura + ")");
        mudarVida(vida + cura);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    // ---- Getters e setters ----

    public void setEstilo(EstiloDeCombate estilo) {
        this.estilo = estilo;
        DiarioDeBatalha.getInstancia().registrar(nome + " muda para o estilo " + estilo.nome());
    }

    public void equipar(Arma arma) {
        this.arma = arma;
        DiarioDeBatalha.getInstancia().registrar(nome + " equipa " + arma.nome()
                + " (dano " + arma.dano() + ")");
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public Arma getArma() {
        return arma;
    }
}
