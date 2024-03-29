package br.com.glp.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Senac PLC Ola olha eu
 */
public class Mensagem {

    public static void sucesso(String msg) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        msg, ""));
    }

    public static void salvar(String msg) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        msg + " salvo com Sucesso.", "")
                );
    }

    public static void campoExiste(String msg) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        msg + " já existe!!", "")
                );
    }

    public static void excluir(String msg) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        msg + " excluído com sucesso!", "")
                );
    }

    public static void clienteNaoExiste(String telefone) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        telefone + " não está cadastrado!", ""));
    }

    public static void senhaAtualNaoConfere() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_FATAL,
                        " Senha atual não confere!", ""));
    }

    public static void senhaNovaIgualSenhaAtual() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        " Senha nova é igual a senha atual!", ""));
    }

    static void valorMenor(double doubleValue) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        doubleValue + " é menor que o total do pedido!", ""));
    }

    static void estoqueInsuficiente(int quantidade, int estoque) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        " Há quantidade " + quantidade + " do pedido é maior que a do estoque, estoque disponível " + estoque, ""));
    }

    static void quantidadeZero() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        " Impossivel add produto com a quanitade 0 'Zero' ", ""));
    }

    static void quantidadeNegativa() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        " Impossivel add produto com a quanitade negativa", ""));
    }

    static void selecioneUmaPesquisa() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        " Por favor selecione 'Pedido' ou 'Produto' para realizar o procedimento ", ""));
    }

    static void dataInicial() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        " Por favor selecione um período inicial para realizar o procedimento ", ""));
    }

    static void dataFinal() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        " Por favor selecione um período final para realizar o procedimento ", ""));
    }

    public static void mensagemError(String msg) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        msg, "")
                );
    }

    public static void selecioneMovimentacao() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                        " Selecione uma Movimentação para realizar o procedimento!", ""));
    }

    public static void campoVazio(String msg) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                       msg + " é Obrigatório ", ""));
    }
    
    public static void dataFinalMenor() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                       "Data final menor que a data inicial", ""));
    }
    
    public static void dataFinalFutura() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                       "Data final maior que a data atual", ""));
    }
    
    public static void dataInicioFutura() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_WARN,
                       "Data inicio maior que a data atual", ""));
    }

}
