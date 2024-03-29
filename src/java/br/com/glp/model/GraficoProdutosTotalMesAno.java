package br.com.glp.model;

/**
 *
 * @author Dhyego Pedroso
 */
public class GraficoProdutosTotalMesAno {

    private int mes;
    private String produto;
    private int quantidade;

    public GraficoProdutosTotalMesAno() {
    }

    public GraficoProdutosTotalMesAno(int mes, String produto, int quantidade) {
        this.mes = mes;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
