package br.com.server;

import br.com.glp.dao.ClienteDao;
import br.com.glp.dao.ClienteDaoImpl;
import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PerfilDao;
import br.com.glp.dao.PerfilDaoImpl;
import br.com.glp.dao.UsuarioDao;
import br.com.glp.dao.UsuarioDaoImpl;
import br.com.glp.model.Cliente;
import br.com.glp.model.Perfil;
import br.com.glp.model.Usuario;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;


@Path("/perfil")
public class UsuarioServer {

    
    
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
     public Usuario autenticar(@FormParam("nome") String nome, @FormParam("senha") String senha){
        Session session = HibernateUtil.abreSessao();
         UsuarioDao usuarioDao = new UsuarioDaoImpl();
         return usuarioDao.pesquisaPorLogin(nome, session);
     }
	 
     
	
}