package Main;
import Banco.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    private static Banco banco = new Banco();

    public static void main(String[] args) {
        int opcao_menu;
        do {
            System.out.println("\n\n----- Menu principal -----\n");
            System.out.println("0 - Sair do menu");
            System.out.println("1 - Criar Conta Corrente");
            System.out.println("2 - Criar Conta Poupança");
            System.out.println("3 - Efetuar Depósito");
            System.out.println("4 - Efetuar Saque");
            System.out.println("5 - Aplicar Correção");
            System.out.println("6 - Cadastrar PIX");
            System.out.println("7 - Efetuar PIX");
            System.out.println("8 - Consultar extrato");
            System.out.println("9 - Ver Saldo");
            System.out.println("10 - Mostrar Contas Cadastradas");
            System.out.print("\nEscolha alguma opção no menu: ");
            opcao_menu = teclado.nextInt();
            teclado.nextLine(); // Limpar o buffer
            switch (opcao_menu) {
                case 1:
                    criarContaCorrente();
                    break;
                case 2:
                    criarContaPoupanca();
                    break;
                case 3:
                    efetuarDeposito();
                    break;
                case 4:
                    efetuarSaque();
                    break;
                case 5:
                    aplicarCorrecao();
                    break;
                case 6:
                    cadastrarPix();
                    break;
                case 7:
                    efetuarPix();
                    break;
                case 8:
                    consultarExtrato();
                    break;
                case 9:
                    verSaldo();
                    break;
                case 10:
                    mostrarContas();
                    break;
            }
        } while (opcao_menu != 0);
        teclado.close();
    }

    public static void criarContaCorrente() {
        System.out.print("Número da Conta: ");
        int numeroConta = teclado.nextInt();

        System.out.print("Nome do Correntista: ");
        teclado.nextLine();
        String nomeCorrentista = teclado.nextLine();

        System.out.print("CPF do Correntista: ");
        String cpfCorrentista = teclado.nextLine();

        System.out.print("Senha da conta: ");
        String senhaConta = teclado.nextLine();

        if (!Conta.isCpfValido(cpfCorrentista)) {
            System.out.println("CPF inválido! O CPF deve conter apenas números.");
            return;
        }
        Banco.criarContaCorrente(numeroConta, nomeCorrentista, cpfCorrentista, senhaConta);
        System.out.println("Conta Corrente criada com sucesso!");
    }

    public static void criarContaPoupanca() {
        System.out.print("Número da Conta: ");
        int numeroConta = teclado.nextInt();

        System.out.print("Nome do Correntista: ");
        teclado.nextLine();
        String nomeCorrentista = teclado.nextLine();

        System.out.print("CPF do Correntista: ");
        String cpfCorrentista = teclado.nextLine();

        System.out.print("Senha da conta: ");
        String senhaConta = teclado.nextLine();

        if (!Conta.isCpfValido(cpfCorrentista)) {
            System.out.println("CPF inválido! O CPF deve conter apenas números.");
            return;
        }
        Banco.criarContaPoupanca(numeroConta, nomeCorrentista, cpfCorrentista, senhaConta);
        System.out.println("Conta Poupança criada com sucesso!");
    }

    public static void efetuarDeposito() {
        System.out.print("Número da conta na qual deseja depositar: ");
        int numeroContaDep = teclado.nextInt();

        System.out.print("Valor do depósito: ");
        float valor = teclado.nextFloat();

        Conta conta = Banco.getConta(numeroContaDep);
        if (conta != null) {
            conta.depositar(valor);
            System.out.println("Depósito de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Conta não encontrada/cadastrada.");
        }
    }

    public static void efetuarSaque() {
        System.out.print("Número da conta na qual deseja sacar: ");
        int numeroConta = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Senha da conta: ");
        String senha = teclado.nextLine();

        Conta conta = Banco.getConta(numeroConta);
        if (conta != null && conta.getSenhaConta().equals(senha)) {
            System.out.print("Valor do saque: ");
            float valor = teclado.nextFloat();
            conta.sacar(valor);
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Conta não encontrada ou senha incorreta.");
        }
    }

    public static void cadastrarPix() {
        System.out.print("CPF do correntista: ");
        String cpf = teclado.nextLine();
        banco.cadastrarPix(cpf);
    }

    public static void efetuarPix() {
        System.out.print("CPF da conta de origem: ");
        String cpfOrigem = teclado.nextLine();
        System.out.print("CPF da conta de destino: ");
        String cpfDestino = teclado.nextLine();
        System.out.print("Valor do PIX: ");
        float valor = teclado.nextFloat();
        if (Banco.realizarPix(cpfOrigem, cpfDestino, valor)) {
            System.out.println("PIX de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Erro ao realizar PIX. Verifique os dados e o saldo da conta de origem.");
        }
    }

    public static void verSaldo() {
        System.out.print("Número da conta: ");
        int numeroConta = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Senha da conta: ");
        String senha = teclado.nextLine();

        Conta conta = Banco.getConta(numeroConta);
        if (conta != null && conta.getSenhaConta().equals(senha)) {
            System.out.println("Saldo da conta: R$ " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada ou senha incorreta.");
        }
    }

    public static void mostrarContas() {
        System.out.print("Nome do Correntista: ");
        String nome = teclado.nextLine();

        ArrayList<Conta> contas = Banco.getContasPorNome(nome);
        if (!contas.isEmpty()) {
            Iterator<Conta> iterator = contas.iterator();
            while (iterator.hasNext()) { // hasNext() verifica se ainda há elementos não percorridos na lista
                Conta conta = iterator.next();
                System.out.println("Número da Conta: " + conta.getNumeroConta());
                System.out.println("CPF do Correntista: " + conta.getCpfCorrentista());
                if (conta instanceof Corrente) {
                    System.out.println("Tipo de Conta: Corrente");
                } else if (conta instanceof Poupanca) {
                    System.out.println("Tipo de Conta: Poupança");
                }
                System.out.println();
            }
        } else {
            System.out.println("Conta não encontrada para o nome fornecido.");
        }
    }

    public static void aplicarCorrecao() {
        System.out.print("Taxa de correção (%): ");
        float taxa = teclado.nextFloat();
        Banco.aplicarCorrecaoEmPoupanca(taxa);
        System.out.println("Correção aplicada em todas as contas poupança com a taxa de " + taxa + "%.");
    }

    public static void consultarExtrato() {
        System.out.print("Número da conta: ");
        int numeroConta = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Senha da conta: ");
        String senha = teclado.nextLine();

        Conta conta = Banco.getConta(numeroConta);
        if (conta != null && conta.getSenhaConta().equals(senha)) {
            String extrato = Banco.consultarExtrato(numeroConta);
            System.out.println(extrato);
        }
    }
}
