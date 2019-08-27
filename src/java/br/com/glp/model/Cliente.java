package br.com.glp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author rossi
 */
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Cliente extends Pessoa implements Serializable {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Caminhao> caminhoes;

    @Column
    private String cnpj;

    @Column
    private String nomeSocial;

    public Cliente() {
    }

    public Cliente(String cnpj, String nomeSocial) {
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
    }

    public Cliente(List<Caminhao> caminhoes, String cnpj, String nomeSocial) {
        this.caminhoes = caminhoes;
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
    }

    public Cliente(String cnpj, String nomeSocial, String nome, Endereco endereco, Contato contato, Date dtCadastro) {
        super(nome, endereco, contato, dtCadastro);
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
    }

    public Cliente(List<Caminhao> caminhoes, String cnpj, String nomeSocial, String nome, Endereco endereco, Contato contato, Date dtCadastro) {
        super(nome, endereco, contato, dtCadastro);
        this.caminhoes = caminhoes;
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
    }

    public Cliente(List<Caminhao> caminhoes, String cnpj, String nomeSocial, Long id, String nome, Endereco endereco, Contato contato, Date dtCadastro) {
        super(id, nome, endereco, contato, dtCadastro);
        this.caminhoes = caminhoes;
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
    }

    public List<Caminhao> getCaminhoes() {
        return caminhoes;
    }

    public void setCaminhoes(List<Caminhao> caminhoes) {
        this.caminhoes = caminhoes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

}
