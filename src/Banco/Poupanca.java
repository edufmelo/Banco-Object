package Banco;
import Interfaces.Remunerada;
public class Poupanca extends Conta implements Remunerada {
    public Poupanca(int numeroConta, String nomeCorrentista, String cpfCorrentista, String senhaConta) {
        super(numeroConta, nomeCorrentista, cpfCorrentista, senhaConta);
    }

    @Override
    public void aplicarCorrecao(float taxa) {
        if (taxa > 0) {
            float saldoAtual = getSaldo();
            float valorCorrecao = saldoAtual * (taxa / 100);
            depositar(valorCorrecao);
            getOperacoes().add(new Operacao("Correção", valorCorrecao, getSaldo()));
        } else {
            throw new IllegalArgumentException("Taxa de correção deve ser positiva.");
        }
    }
}
