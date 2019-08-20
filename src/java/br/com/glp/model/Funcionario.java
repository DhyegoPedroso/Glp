package br.com.glp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Funcionario extends Pessoa implements Serializable {

    @Column
    private String rg;

    @Column
    private String cpf;

    @Column
    private String matricula;

    @Column
    private String sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Funcionario() {
    }

    public Funcionario(String nome, Endereco endereco, Contato contato, Date dtCadastro) {
        super(nome, endereco, contato, dtCadastro);
    }

    public Funcionario(Long id, String nome, Endereco endereco, Contato contato, Date dtCadastro) {
        super(id, nome, endereco, contato, dtCadastro);
    }

    public Funcionario(String rg, String cpf, String matricula, String sexo, Usuario usuario) {
        this.rg = rg;
        this.cpf = cpf;
        this.matricula = matricula;
        this.sexo = sexo;
        this.usuario = usuario;
    }

    public Funcionario(String rg, String cpf, String matricula, String sexo, Usuario usuario, String nome, Endereco endereco, Contato contato, Date dtCadastro) {
        super(nome, endereco, contato, dtCadastro);
        this.rg = rg;
        this.cpf = cpf;
        this.matricula = matricula;
        this.sexo = sexo;
        this.usuario = usuario;
    }

    public Funcionario(String rg, String cpf, String matricula, String sexo, Usuario usuario, Long id, String nome, Endereco endereco, Contato contato, Date dtCadastro) {
        super(id, nome, endereco, contato, dtCadastro);
        this.rg = rg;
        this.cpf = cpf;
        this.matricula = matricula;
        this.sexo = sexo;
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

}
