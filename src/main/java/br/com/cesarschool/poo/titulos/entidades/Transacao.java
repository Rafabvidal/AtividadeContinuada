package br.com.cesarschool.poo.titulos.entidades;

import br.com.cesarschool.poo.titulos.utils.Comparavel;
import br.gov.cesarschool.poo.daogenerico.Entidade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao extends Entidade implements Comparavel{

    private EntidadeOperadora entidadeCredito;
    private EntidadeOperadora entidadeDebito;
    private Acao acao;
    private TituloDivida tituloDivida;
    private double valorOperacao;
    private LocalDateTime dataHoraOperacao;

    public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao,
                     TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
        this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }

    public EntidadeOperadora getEntidadeCredito() {
        return entidadeCredito;
    }

    public void setEntidadeCredito(EntidadeOperadora entidadeCredito) {
        this.entidadeCredito = entidadeCredito;
    }

    public EntidadeOperadora getEntidadeDebito() {
        return entidadeDebito;
    }

    public void setEntidadeDebito(EntidadeOperadora entidadeDebito) {
        this.entidadeDebito = entidadeDebito;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public TituloDivida getTituloDivida() {
        return tituloDivida;
    }

    public void setTituloDivida(TituloDivida tituloDivida) {
        this.tituloDivida = tituloDivida;
    }

    public double getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }

    public void setDataHoraOperacao(LocalDateTime dataHoraOperacao) {
        this.dataHoraOperacao = dataHoraOperacao;
    }

    @Override
    public String getIdUnico() {
        String idAcaoOuTitulo = (acao != null ? acao.getIdUnico() : (tituloDivida != null ? tituloDivida.getIdUnico() : ""));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dataHoraFormatada = dataHoraOperacao.format(formatter);
        return entidadeCredito.getIdUnico() + "_" + entidadeDebito.getIdUnico() + "_" + idAcaoOuTitulo + "_" + dataHoraFormatada;
    }

    @Override
    public int comparar(Comparavel c) {
        if (c instanceof Transacao) {
            Transacao outraTransacao = (Transacao) c;
            //System.out.println("hi");
            return (this.dataHoraOperacao.compareTo(outraTransacao.dataHoraOperacao)) * - 1;
        }
        throw new IllegalArgumentException("O objeto comparado deve ser uma instância de Transacao.");
    }
}
