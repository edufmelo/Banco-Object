package Banco;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operacao {
    private LocalDateTime data;
    private String tipo;
    private float valor;
    private float saldo;

    public Operacao(String tipo, float valor, float saldo) {
        this.data = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.saldo = saldo;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return data.format(formatter);
    }

    public String getTipo() {
        return tipo;
    }

    public float getValor() {
        return valor;
    }

    public float getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return getDataFormatada() + " " + getTipo() + " " + String.format("%.2f", getValor()) + "\nSaldo R$" + String.format("%.2f", getSaldo());
    }
}
