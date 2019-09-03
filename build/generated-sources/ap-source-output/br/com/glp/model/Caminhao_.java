package br.com.glp.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Caminhao.class)
public abstract class Caminhao_ {

	public static volatile SingularAttribute<Caminhao, String> placaCaminhao;
	public static volatile SingularAttribute<Caminhao, Endereco> endereco;
	public static volatile SingularAttribute<Caminhao, String> nomeMotorista;
	public static volatile SingularAttribute<Caminhao, Long> id;

}

