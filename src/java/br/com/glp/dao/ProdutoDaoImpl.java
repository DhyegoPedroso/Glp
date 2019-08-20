package br.com.glp.dao;

import br.com.glp.model.Produto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProdutoDaoImpl extends BaseDaoImpl<Produto, Long> implements ProdutoDao, Serializable {

    @Override
    public Produto pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Produto) session.get(Produto.class, id);
    }

    @Override
    public List<Produto> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Produto").list();
    }

    @Override
    public List<Produto> pesquisaPorNome(String nomeProduto, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Produto p where p.nomeProduto like :nomeProduto");
        consulta.setParameter("nomeProduto", nomeProduto + "%");
        return consulta.list();
    }

    @Override
    public List<Produto> listarPorTipo(String nomeProduto, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Produto p where p.nomeProduto like :nomeProduto");
        consulta.setParameter("nomeProduto", nomeProduto + "%");
        return consulta.list();
    }

    @Override
    public List<Produto> listarPorMarca(String marcaProduto, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Produto p where p.marcaProduto like :marcaProduto");
        consulta.setParameter("marcaProduto", "%" + marcaProduto + "%");
        return consulta.list();
    }

}
