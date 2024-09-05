package Banco;

import Interfaces.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Banco implements Pix {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static ArrayList<String> usuariosPix = new ArrayList<>();

    public static void criarContaCorrente(int numeroConta, String nomeCorrentista, String cpfCorrentista, String senhaConta) {
        Corrente contaCorrente = new Corrente(numeroConta, nomeCorrentista, cpfCorrentista, senhaConta);
        contas.add(contaCorrente);
    }

    public static void criarContaPoupanca(int numeroConta, String nomeCorrentista, String cpfCorrentista, String senhaConta) {
        Poupanca contaPoupanca = new Poupanca(numeroConta, nomeCorrentista, cpfCorrentista, senhaConta);
        contas.add(contaPoupanca);
    }

    public static Conta getConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public static Conta getContaPorCpf(String cpf) {
        for (Conta conta : contas) {
            if (conta.getCpfCorrentista().equals(cpf)) {
                return conta;
            }
        }
        return null;
    }


    @Override
    public void cadastrarPix(String cpf) {
        Conta conta = getContaPorCpf(cpf);
        if (conta != null && conta instanceof Corrente && !usuariosPix.contains(cpf)) {
            usuariosPix.add(cpf);
            System.out.println("CPF cadastrado para PIX com sucesso!");
        } else {
            System.out.println("CPF não encontrado, já cadastrado, ou não é uma conta corrente.");
        }
    }

    @Override
    public void efetuarPix(String cpfDestino, float valor) {
        Conta contaDestino = getContaPorCpf(cpfDestino);
        if (contaDestino != null && usuariosPix.contains(cpfDestino)) {
            contaDestino.receberPix(valor);
            System.out.println("PIX de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Erro ao realizar PIX. Destinatário não encontrado ou não autorizado.");
        }
    }

    @Override
    public void receberPix(String cpfOrigem, float valor) {
        Conta contaOrigem = getContaPorCpf(cpfOrigem);
        if (contaOrigem != null && usuariosPix.contains(cpfOrigem)) {
            contaOrigem.efetuarPix(valor);
            System.out.println("PIX recebido de R$" + valor + " com sucesso!");
        } else {
            System.out.println("Erro ao receber PIX. Origem não encontrada ou não autorizada.");
        }
    }

    public static boolean realizarPix(String cpfOrigem, String cpfDestino, float valor) {
        Conta contaOrigem = getContaPorCpf(cpfOrigem);
        Conta contaDestino = getContaPorCpf(cpfDestino);
        if (contaOrigem != null && contaDestino != null && usuariosPix.contains(cpfDestino) && usuariosPix.contains(cpfOrigem)) {
            try {
                contaOrigem.efetuarPix(valor);
                contaDestino.receberPix(valor);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());     // Mensagem para descrever o erro ocorrido
                return false;
            }
        } else {
            return false;
        }
    }

    public static ArrayList<Conta> getContasPorNome(String nome) {
        ArrayList<Conta> contasEncontradas = new ArrayList<>();
        for (Conta conta : contas) {
            if (conta.getNomeCorrentista().equalsIgnoreCase(nome)) {
                contasEncontradas.add(conta);
            }
        }
        return contasEncontradas;
    }
    public static void aplicarCorrecaoEmPoupanca(float taxa) {
        for (Conta conta : contas) {
            if (conta instanceof Poupanca) {
                ((Poupanca) conta).aplicarCorrecao(taxa); // casting para chamar metodo especifico da classe poupança
                // (Conta pode ser corrente ou poupança)
            }
        }
    }
    public static String consultarExtrato(int numeroConta) {
        Conta conta = getConta(numeroConta);
        if (conta != null) {
            StringBuilder extrato = new StringBuilder();
            for (Operacao operacao : conta.getOperacoes()) {
                extrato.append(operacao.toString()).append("\n");
            }
            return extrato.toString();
        } else {
            return "Conta não encontrada.";
        }
    }
}
