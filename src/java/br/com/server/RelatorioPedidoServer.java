/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.server;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.Pedido;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;

/**
 *
 * @author Pedrão
 */
public class RelatorioPedidoServer {
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     public List<Pedido> getPedido(){
        Session session = HibernateUtil.abreSessao();
        PedidoDao pedidoDao = new PedidoDaoImpl();
        return pedidoDao.listaTodos(session);
     }
     
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     @Path("{id}")
     public Pedido getPedido(@PathParam("id") Long id){
        Session session = HibernateUtil.abreSessao();
        PedidoDao pedidoDao = new PedidoDaoImpl();
        return pedidoDao.pesquisaEntidadeId(id, session);
     }
     
}
