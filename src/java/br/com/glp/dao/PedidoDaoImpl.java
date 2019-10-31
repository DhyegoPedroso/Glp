/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.glp.dao;

import br.com.glp.model.Pedido;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public class PedidoDaoImpl extends BaseDaoImpl<Pedido, Long> implements PedidoDao {

    @Override
    public Pedido pesquisaEntidadeId(Long id, Session session) throws HibernateException {
        return (Pedido) session.get(Pedido.class, id);
    }

    @Override
    public List<Pedido> listaTodos(Session session) throws HibernateException {
        return session.createQuery("from Pedido").list();
    }

    @Override
    public List<Pedido> pesquisaPorNome(String nomeSocial, Session session) throws HibernateException {
        return null;
    }

    @Override
    public List<Pedido> pesquisaPedidoNomeSocial(String nomeSocial, Session session) throws HibernateException {
         Query consulta = session.createQuery("select p from Pedido p join p.cliente c where c.nomeSocial like :nomeSocial");
        consulta.setParameter("nomeSocial", "%" + nomeSocial + "%");
        return consulta.list();
    }

}
