package br.com.glp.dao;

import br.com.glp.model.Produto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pedro Mattos
 */
public interface ProdutoDao extends BaseDao<Produto, Long> {
    
    List<Produto> listarPorTipo(String tipoProduto, Session session) throws HibernateException;
    List<Produto> listarPorMarca(String marcaProduto, Session session) throws HibernateException;
    
}
