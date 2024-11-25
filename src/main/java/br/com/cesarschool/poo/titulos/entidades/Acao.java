package br.com.cesarschool.poo.titulos.entidades;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Acao extends Ativo implements Serializable {

    private static final long serialVersionUID = 1L;

    private double valorUnitario;
    private LocalDateTime dataHoraInclusao;

    public Acao(int identificador, String nome, LocalDate dataDeValidade, double valorUnitario) {
        super(identificador, nome, dataDeValidade);
        this.valorUnitario = valorUnitario;
        this.dataHoraInclusao = LocalDateTime.now(); // Inicializa ao criar o objeto
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        if (dataHoraInclusao != null) {
            this.dataHoraInclusao = dataHoraInclusao;
        }
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double calcularPrecoTransacao(double montante) {
        return montante * valorUnitario;
    }

    @Override
    public String toString() {
        return "Valor Unitário: R$" + valorUnitario + ", Data de Inclusão: " + dataHoraInclusao;
    }
}
