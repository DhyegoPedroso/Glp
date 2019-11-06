/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.glp.dao;

import br.com.glp.model.Pedido;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Pedrão Master
 */
public interface PedidoDao extends BaseDao<Pedido, Long> {

    public List<Pedido> pesquisaPedidoNomeSocial(String nomeSocial, Session session) throws HibernateException;

    public List<Pedido> pesquisaPedidoClienteDataInicioFim(String nomeSocial, Date inicio, Date fim, Session session) throws HibernateException;
    
    public List<Pedido> listarTodosPedidoDataInicioFim(Date inicio, Date fim, Session session) throws HibernateException;

}
