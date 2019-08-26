package br.com.glp.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ extends br.com.glp.model.Pessoa_ {

	public static volatile SingularAttribute<Funcionario, String> rg;
	public static volatile SingularAttribute<Funcionario, String> cpf;
	public static volatile SingularAttribute<Funcionario, String> matricula;
	public static volatile SingularAttribute<Funcionario, Usuario> usuario;
	public static volatile SingularAttribute<Funcionario, String> sexo;

}

