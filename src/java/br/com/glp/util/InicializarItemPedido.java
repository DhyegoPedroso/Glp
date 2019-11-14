package br.com.glp.util;

import br.com.glp.dao.HibernateUtil;
import br.com.glp.dao.ItemPedidoDao;
import br.com.glp.dao.ItemPedidoDaoImpl;
import br.com.glp.model.ItemPedido;
import br.com.glp.model.Pedido;
import br.com.glp.model.Produto;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Dhyego Pedroso
 */
public class InicializarItemPedido {

    Session session;

    public void InicializarItemPedido() {

        try {

            session = HibernateUtil.abreSessao();

            Long id = 0L;

            for (int i = 0; i < 2000; i++) {

                List<ItemPedido> itemPedidos = new ArrayList<>();

                Pedido pedido = new Pedido();
                id++;
                pedido.setId(id);

                ItemPedido itemPedido1 = new ItemPedido(gerarQuantidade(), gerarMovimentacao(), gerarProduto(1, 9), pedido);
                itemPedidos.add(itemPedido1);
                ItemPedido itemPedido2 = new ItemPedido(gerarQuantidade(), gerarMovimentacao(), gerarProduto(1, 9), pedido);
                itemPedidos.add(itemPedido2);
                ItemPedido itemPedido3 = new ItemPedido(gerarQuantidade(), gerarMovimentacao(), gerarProduto(1, 9), pedido);
                itemPedidos.add(itemPedido3);
                ItemPedido itemPedido4 = new ItemPedido(gerarQuantidade(), gerarMovimentacao(), gerarProduto(1, 9), pedido);
                itemPedidos.add(itemPedido4);

                ItemPedidoDao itemPedidoDao = new ItemPedidoDaoImpl();
                itemPedidoDao.salvarOuAlterar(itemPedido1, session);
                itemPedidoDao.salvarOuAlterar(itemPedido2, session);
                itemPedidoDao.salvarOuAlterar(itemPedido3, session);
                itemPedidoDao.salvarOuAlterar(itemPedido4, session);
            }

        } catch (NumberFormatException e) {
            session.close();
        }

    }

    public static Produto gerarProduto(int incio, int fim) {
        Produto produto = new Produto();
        produto.setId(incio + (Long) Math.round(Math.random() * (fim - incio)));
        return produto;
    }

    public static String gerarMovimentacao() {
        String movimentacao = String.valueOf((1 + (int) Math.round(Math.random() * (2 - 1))));

        if (movimentacao.equalsIgnoreCase("1")) {
            movimentacao = "Entrada";
        } else {
            movimentacao = "Saida";
        }
        return movimentacao;
    }

    public static Integer gerarQuantidade() {
        int quantidade = (1 + (int) Math.round(Math.random() * (450 - 1)));
        return quantidade;
    }

}
