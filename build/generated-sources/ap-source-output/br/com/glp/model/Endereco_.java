package br.com.glp.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, String> cidade;
	public static volatile SingularAttribute<Endereco, Integer> numero;
	public static volatile SingularAttribute<Endereco, String> bairro;
	public static volatile SingularAttribute<Endereco, String> unidade;
	public static volatile SingularAttribute<Endereco, String> cep;
	public static volatile SingularAttribute<Endereco, String> pais;
	public static volatile SingularAttribute<Endereco, String> uf;
	public static volatile SingularAttribute<Endereco, Cliente> cliente;
	public static volatile SingularAttribute<Endereco, String> complemento;
	public static volatile ListAttribute<Endereco, Caminhao> caminhoes;
	public static volatile SingularAttribute<Endereco, String> logradouro;
	public static volatile SingularAttribute<Endereco, Long> id;
	public static volatile SingularAttribute<Endereco, Contato> contato;

}

