package br.com.rpg.decorator;

/** Este encantamento multiplica em vez de somar, então a ordem importa. */
public class BencaoLendaria extends Encantamento {

    public BencaoLendaria(Arma arma) {
        super(arma);
    }

    @Override
    public int dano() {
        return arma.dano() * 2;
    }

    @Override
    public String nome() {
        return arma.nome() + " lendária";
    }
}
