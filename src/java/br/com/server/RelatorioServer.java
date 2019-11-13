/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.server;

import br.com.glp.dao.ClienteDao;
import br.com.glp.dao.ClienteDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.model.Cliente;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;

/**
 *
 * @author Pedro
 */
@Path("/relatorio")
public class RelatorioServer {
    
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     public List<Cliente> getCategoria(){
        Session session = HibernateUtil.abreSessao();
        ClienteDao clienteDao = new ClienteDaoImpl();
        return clienteDao.listaTodos(session);
     }
}
