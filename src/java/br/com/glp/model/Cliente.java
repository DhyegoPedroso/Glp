package br.com.glp.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String nomeSocial;

    public Cliente() {
    }

    public Cliente(String cnpj, String nomeSocial) {
        this.cnpj = cnpj;
        this.nomeSocial = nomeSocial;
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

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
