package br.com.glp.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, String> marca;
	public static volatile SingularAttribute<Produto, String> situacao;
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, Integer> quantidade;
	public static volatile SingularAttribute<Produto, String> nomeProduto;

}

