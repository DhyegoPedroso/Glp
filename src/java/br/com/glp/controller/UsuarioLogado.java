package br.com.glp.controller;

import br.com.glp.dao.UsuarioDao;
import br.com.glp.dao.UsuarioDaoImpl;
import br.com.glp.model.Usuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Dhyego Pedroso
 */

@ManagedBean(name = "usuarioC")
@ViewScoped
public class UsuarioLogado implements Serializable{

    private Usuario usuario;
    private UsuarioDao usuarioDao;

    public Usuario usuarioLogadoSpring(Session session) {
        String login = null;
        usuario = new Usuario();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                login = ((User) authentication.getPrincipal()).getUsername();
            }
        }
        usuarioDao = new UsuarioDaoImpl();
        try {
            usuario = usuarioDao.pesquisaPorLogin(login, session);
        } catch (HibernateException he) {
            System.out.println("Erro ao pegar usuarioLogadoSpring " + he.getMessage());
        }
        
        return usuario;
    }

}
