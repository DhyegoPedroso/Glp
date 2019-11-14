package br.com.glp.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;

/**
 *
 * @author Pedrão, Dhyego
 */
public class GeradorTabela {

    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("glpPU");
        emf.close();

        InicializarPerfil perfil = new InicializarPerfil();
        perfil.iniciarPerfils();

        InicializarCliente cliente = new InicializarCliente();
        cliente.inicializarClientes();

        InicializarProduto produto = new InicializarProduto();
        produto.inicializarProdutos();

        InicializarPedido pedido = new InicializarPedido();
        pedido.inicializarPedido();

        InicializarItemPedido itemPedido = new InicializarItemPedido();
        itemPedido.InicializarItemPedido();

    }
}
