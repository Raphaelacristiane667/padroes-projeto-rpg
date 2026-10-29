package br.com.rpg.factorymethod;

import br.com.rpg.personagem.Inimigo;
import br.com.rpg.singleton.DiarioDeBatalha;

import java.util.ArrayList;
import java.util.List;

/**
 * FACTORY METHOD (versão GoF): toda região do mapa gera ondas de inimigos
 * do mesmo jeito (método gerarOnda), mas QUAL inimigo nasce é decidido
 * pelas subclasses, no método abstrato criarInimigo().
 *
 * Para uma região nova, basta criar outra subclasse, sem mexer nesta classe
 * nem nas regiões que já existem (princípio aberto/fechado).
 */
public abstract class Regiao {

    // O factory method
    protected abstract Inimigo criarInimigo(int numero);

    public abstract String nome();

    public List<Inimigo> gerarOnda(int quantidade) {
        DiarioDeBatalha.getInstancia().registrar("Você chegou à região " + nome());
        List<Inimigo> onda = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Inimigo inimigo = criarInimigo(i);
            DiarioDeBatalha.getInstancia().registrar(inimigo.getNome() + " aparece: "
                    + inimigo.gritoDeGuerra());
            onda.add(inimigo);
        }
        return onda;
    }
}
