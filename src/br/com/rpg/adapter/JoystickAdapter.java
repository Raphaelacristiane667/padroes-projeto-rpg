package br.com.rpg.adapter;

/**
 * ADAPTER: implementa Controle, que é o que o jogo conhece, e por dentro
 * traduz os códigos de botão do JoystickRetro para ações do jogo.
 * Se amanhã trocarmos por um teclado ou um controle de outro fabricante,
 * só muda o adapter; a batalha continua igual.
 */
public class JoystickAdapter implements Controle {

    private final JoystickRetro joystick;

    public JoystickAdapter(JoystickRetro joystick) {
        this.joystick = joystick;
    }

    @Override
    public Acao proximaAcao() {
        int codigo = joystick.lerCodigoDoBotao();
        return switch (codigo) {
            case JoystickRetro.BOTAO_A -> Acao.ATACAR;
            case JoystickRetro.BOTAO_B -> Acao.DEFENDER;
            case JoystickRetro.BOTAO_X -> Acao.BEBER_POCAO;
            case JoystickRetro.BOTAO_Y -> Acao.FURIA;
            default -> throw new IllegalStateException("Botão desconhecido: " + codigo);
        };
    }
}
