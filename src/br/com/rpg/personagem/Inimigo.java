package br.com.rpg.personagem;

/** Base de todos os inimigos. Cada tipo define seus atributos e seu ataque. */
public abstract class Inimigo {

    private final String nome;
    private int vida;

    protected Inimigo(String nome, int vida) {
        this.nome = nome;
        this.vida = vida;
    }

    public abstract int ataque();

    public abstract String gritoDeGuerra();

    public void receberDano(int dano) {
        vida = Math.max(0, vida - dano);
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }
}
