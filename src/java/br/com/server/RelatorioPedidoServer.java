/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.server;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.PedidoDao;
import br.com.glp.dao.PedidoDaoImpl;
import br.com.glp.model.RelatorioPedido;
import com.google.gson.Gson;
import java.util.ArrayList;
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

        Gson gson = new Gson();

        // converte objetos Java para JSON e retorna JSON como String
        String json = gson.toJson(pedidos);

        return json;
    }

}
//@Path("/relatorioProduto")
//public class RelatorioProdutoServer {
//     @GET
//     @Produces(MediaType.APPLICATION_JSON)
//     public List<Produto> getProduto(){
//        Session session = HibernateUtil.abreSessao();
//        ProdutoDao produtoDao = new ProdutoDaoImpl();
//        return produtoDao.listaTodos(session);
//     }
//}
