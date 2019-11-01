package br.com.glp.dao;

import br.com.glp.model.Estoque;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public interface EstoqueDao extends BaseDao<Estoque, Long> {

    List<Estoque> ultimaSituacaoEstoque(Session session) throws HibernateException;

    Long ultimoIdEstoque(Session session) throws HibernateException;

}
