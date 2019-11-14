package br.com.server;

import br.com.glp.dao.ClienteDao;
import br.com.glp.dao.ClienteDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.model.Cliente;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;


@Path("/cliente")
public class ClienteServer {

    
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     public List<Cliente> getCategoria(){
        Session session = HibernateUtil.abreSessao();
        ClienteDao clienteDao = new ClienteDaoImpl();
        return clienteDao.listaTodos(session);
     }
     
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     @Path("{nome}")
     public Cliente getClientePorNome(@PathParam("nome") String nome){
        Session session = HibernateUtil.abreSessao();
        ClienteDao clienteDao = new ClienteDaoImpl();
        return clienteDao.pesquisaPorNome(nome, session).get(0);
     }
     
     
    
    
	 
     
	
}