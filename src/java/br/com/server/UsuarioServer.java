package br.com.server;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.UsuarioDao;
import br.com.glp.dao.UsuarioDaoImpl;
import br.com.glp.model.Usuario;
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

@Path("/perfil")
public class UsuarioServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{login}/{senha}")
    public Usuario autentica(@PathParam("login") String login, @PathParam("senha") String senha) {
        Session session = HibernateUtil.abreSessao();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        return usuarioDao.logar(login, senha, session);

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Usuario autenticar(@FormParam("login") String login, @FormParam("senha") String senha) {
        Session session = HibernateUtil.abreSessao();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        return usuarioDao.logar(login, senha, session);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listaUsuario() {
        Session session = HibernateUtil.abreSessao();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        return usuarioDao.listaTodos(session);
    }

}
