/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja;

import javax.swing.JOptionPane;
import loja.erros.ConexaoException;
import loja.erros.RepositorioException;
import loja.erros.ValidacaoException;
import loja.negocio.RNCliente;
import loja.negocio.basica.Cliente;
import loja.util.GerenciadorConexaoMysql;

/**
 *
 * @author Programador
 */
public class Loja {
    private static final GerenciadorConexaoMysql conn = GerenciadorConexaoMysql.getInstancia();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //testeInserir();
        //testeAlterar();
        testeExcluir();
    }

    public static void testeInserir() {
        try {
            RNCliente rn = new RNCliente();
            Cliente c = new Cliente();
            c.setNome("Doido véi");
            c.setCpf("12345678915");
            c.setFone("999999999");
            c.setEmail("rhuan@hotmail.com");
            c.setEndereco("Av Unibratec");
            c.setData_cadastro("2016-05-26");
            rn.salvar(c);
            JOptionPane.showMessageDialog(null, "Funfou");
        } catch (ConexaoException c) {
            JOptionPane.showMessageDialog(null, "pau na conexao");
        } catch (RepositorioException r) {
            JOptionPane.showMessageDialog(null, "pau no repositorio");
        } catch (ValidacaoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void testeAlterar() {
        try {
            RNCliente rn = new RNCliente();
            Cliente c = new Cliente();
            c.setId(1);
            c.setNome("Enoque Souza");
            c.setCpf("12345678910");
            c.setFone("988888888");
            c.setEmail("rhuan@hotmail.com");
            c.setEndereco("Rua Unibratec");
            c.setData_cadastro("2016-05-26");
            rn.alterar(c);
            JOptionPane.showMessageDialog(null, "Funfou");
        } catch (ConexaoException c) {
            JOptionPane.showMessageDialog(null, "pau na conexao");
        } catch (RepositorioException r) {
            JOptionPane.showMessageDialog(null, "pau no repositorio");
        } catch (ValidacaoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private static void testeExcluir() {
        try{
            RNCliente rn = new RNCliente();
            Cliente c = new Cliente();
            c.setId(10);
            rn.excluir(c);
            JOptionPane.showMessageDialog(null, "Funfou");
        } catch (ConexaoException c) {
            JOptionPane.showMessageDialog(null, "pau na conexao");
        } catch (RepositorioException r) {
            JOptionPane.showMessageDialog(null, "pau no repositorio");
        } catch (ValidacaoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    } 
}