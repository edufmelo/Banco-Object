package Banco;
import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class Conta {
    private int numeroConta;
    private String nomeCorrentista;
    private String cpfCorrentista;
    private String senhaConta;
    protected float saldo;
    private ArrayList<Operacao> operacoes;

    public Conta(int numeroConta, String nomeCorrentista, String cpfCorrentista, String senhaConta) {
        this.numeroConta = numeroConta;
        this.nomeCorrentista = nomeCorrentista;
        this.cpfCorrentista = cpfCorrentista;
        this.senhaConta = senhaConta;
        this.saldo = 0;
        this.operacoes = new ArrayList<>();
    }

    public void depositar(float valor) {
        if (valor > 0) {
            saldo += valor;
            operacoes.add(new Operacao("Deposito R$", valor, saldo));
        } else {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        }
    }

    public void sacar(float valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            operacoes.add(new Operacao("Saque R$", valor, saldo));
        } else {
            throw new IllegalArgumentException("Valor do saque inválido.");
        }
    }

    public void receberPix(float valor) {
        if (valor > 0) {
            saldo += valor;
            operacoes.add(new Operacao("PIX In R$", valor, saldo));
        } else {
            throw new IllegalArgumentException("Valor do PIX deve ser positivo.");
        }
    }

    public void efetuarPix(float valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            operacoes.add(new Operacao("PIX Out R$", valor, saldo));
        } else {
            throw new IllegalArgumentException("Valor do PIX inválido.");
        }
    }

    public ArrayList<Operacao> getOperacoes() {
        return operacoes;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public String getCpfCorrentista() {
        return cpfCorrentista;
    }

    public String getSenhaConta() {
        return senhaConta;
    }

    public float getSaldo() {
        return saldo;
    }
    public static boolean isCpfValido(String cpf) {
        return cpf != null && cpf.matches("\\d+");
    }

}
