package br.com.glp.model;

import java.io.Serializable;

/**
 *
 * @author Dhyego Pedroso
 */
public class GraficoPedidosTotalMesAno implements Serializable{
    
    private int mes;
    private long quantidade;

    public GraficoPedidosTotalMesAno() {
    }

    public GraficoPedidosTotalMesAno(int mes, long quantidade) {
        this.mes = mes;
        this.quantidade = quantidade;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }
    
}
