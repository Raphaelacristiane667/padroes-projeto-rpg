package br.com.rpg.adapter;

/**
 * Classe ADAPTADA: simula o driver de um joystick antigo, que não podemos
 * alterar. Ele não sabe nada de "atacar" ou "defender": só devolve o código
 * hexadecimal do botão apertado. Aqui ele reproduz uma sequência gravada.
 */
public class JoystickRetro {

    public static final int BOTAO_A = 0x01;
    public static final int BOTAO_B = 0x02;
    public static final int BOTAO_X = 0x04;
    public static final int BOTAO_Y = 0x08;

    private final int[] sequenciaGravada;
    private int posicao = 0;

    public JoystickRetro(int... sequenciaGravada) {
        this.sequenciaGravada = sequenciaGravada;
    }

    public int lerCodigoDoBotao() {
        int codigo = sequenciaGravada[posicao % sequenciaGravada.length];
        posicao++;
        return codigo;
    }
}
