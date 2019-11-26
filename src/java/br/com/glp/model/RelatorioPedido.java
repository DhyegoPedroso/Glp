/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.glp.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Pedrão
 */
public class RelatorioPedido {
    private String cliente;
    private String cnpj;
    private String placaDoVeiculo;
    private String nomeMotorista;
    private String dataPedido;

    public RelatorioPedido(String cliente, String cnpj, String placaDoVeiculo, String nomeMotorista, String dataPedido) {
        this.cliente = cliente;
        this.cnpj = cnpj;
        this.placaDoVeiculo = placaDoVeiculo;
        this.nomeMotorista = nomeMotorista;
        this.dataPedido = dataPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPlacaDoVeiculo() {
        return placaDoVeiculo;
    }

    public void setPlacaDoVeiculo(String placaDoVeiculo) {
        this.placaDoVeiculo = placaDoVeiculo;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RelatorioPedido other = (RelatorioPedido) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.placaDoVeiculo, other.placaDoVeiculo)) {
            return false;
        }
        if (!Objects.equals(this.nomeMotorista, other.nomeMotorista)) {
            return false;
        }
        if (!Objects.equals(this.dataPedido, other.dataPedido)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RelatorioPedido{" + "cliente=" + cliente + ", cnpj=" + cnpj + ", placaDoVeiculo=" + placaDoVeiculo + ", nomeMotorista=" + nomeMotorista + ", dataPedido=" + dataPedido + '}';
    }
    
    
    
    
    
    

  
    

    
    
}
