package br.com.rpg.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SINGLETON: um único diário registra tudo o que acontece na partida.
 * Qualquer classe do jogo escreve nele e todas veem o mesmo histórico.
 *
 * Usa o idioma "holder": a classe interna só é carregada pela JVM no
 * primeiro getInstancia(), o que deixa a criação preguiçosa e segura
 * entre threads sem precisar de synchronized.
 */
public final class DiarioDeBatalha {

    private final List<String> registros = new ArrayList<>();
    private int turno = 1;

    private DiarioDeBatalha() {
    }

    private static class Holder {
        private static final DiarioDeBatalha INSTANCIA = new DiarioDeBatalha();
    }

    public static DiarioDeBatalha getInstancia() {
        return Holder.INSTANCIA;
    }

    public synchronized void registrar(String evento) {
        String linha = "[T" + turno + "] " + evento;
        registros.add(linha);
        System.out.println(linha);
    }

    public synchronized void proximoTurno() {
        turno++;
    }

    public synchronized List<String> getRegistros() {
        return Collections.unmodifiableList(new ArrayList<>(registros));
    }
}
