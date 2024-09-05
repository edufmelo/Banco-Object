package Banco;
import Interfaces.Pix;
import java.util.ArrayList;

public class Corrente extends Conta implements Pix {
    private static ArrayList<String> usuariosPix = new ArrayList<>();

    public Corrente(int numeroConta, String nomeCorrentista, String cpfCorrentista, String senhaConta) {
        super(numeroConta, nomeCorrentista, cpfCorrentista, senhaConta);
    }

    public void cadastrarPix(String cpf) {
        if (!usuariosPix.contains(cpf)) {
            usuariosPix.add(cpf);
        } else {
            throw new IllegalArgumentException("Usuário já possui PIX.");
        }
    }

    public void efetuarPix(String cpfDestino, float valor) {
        if (usuariosPix.contains(getCpfCorrentista())) {
            if (valor > 0 && valor <= getSaldo()) {
                sacar(valor);
            } else {
                throw new IllegalArgumentException("Saldo insuficiente ou valor inválido para transferência.");
            }
        } else {
            throw new IllegalArgumentException("Usuário não cadastrado no PIX.");
        }
    }

    public void receberPix(String cpfOrigem, float valor) {
        if (valor > 0) {
            depositar(valor);
        } else {
            throw new IllegalArgumentException("Valor inválido para recebimento via PIX.");
        }
    }

}
