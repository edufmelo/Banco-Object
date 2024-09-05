package Interfaces;
public interface Pix {
    void cadastrarPix(String cpf);
    void efetuarPix(String cpfDestino, float valor);
    void receberPix(String cpfOrigem, float valor);
}
