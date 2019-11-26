package br.com.server;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.RelatorioPedido;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;

/**
 *
 * @author Pedrão
 */
@Path("/relatorioPedido")
public class RelatorioPedidoServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPedido() {
        Session session = HibernateUtil.abreSessao();
        PedidoDao pedidoDao = new PedidoDaoImpl();
        List<RelatorioPedido> pedidos = pedidoDao.listarTodoMobile(session);
        Gson pedidoGson = new Gson();
        return pedidoGson.toJson(pedidos);
    }

}
