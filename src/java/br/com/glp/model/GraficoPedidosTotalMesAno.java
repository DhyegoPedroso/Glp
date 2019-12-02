package br.com.glp.model;

import java.io.Serializable;

/**
 *
 * @author Dhyego Pedroso
 */
public class GraficoPedidosTotalMesAno implements Serializable{
    
    private int mes;
    private int quantidade;

    public GraficoPedidosTotalMesAno() {
    }

    public GraficoPedidosTotalMesAno(int mes, int quantidade) {
        this.mes = mes;
        this.quantidade = quantidade;
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
