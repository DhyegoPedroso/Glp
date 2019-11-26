/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.glp.model;

import java.util.Date;

/**
 *
 * @author Alunos
 */
public class RelatorioPedido {
    private String dataPedido;
    private String nome;
    private String motorista;
    private String placa;
    private String cnpj;

    public RelatorioPedido(String dataPedido, String nome, String motorista, String placa, String cnpj) {
        this.dataPedido = dataPedido;
        this.nome = nome;
        this.motorista = motorista;
        this.placa = placa;
        this.cnpj = cnpj;
    }
    
    

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
