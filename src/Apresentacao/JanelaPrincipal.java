package Apresentacao;

import Modelo.Cliente;
import Modelo.Endereco;
import Modelo.Imagem;
import Modelo.Telefone;
import Persistencia.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class JanelaPrincipal extends JFrame {

    JLabel lWallpaper;
    private BarradeMenu MenuB;

    public JanelaPrincipal(){

        Imagem wallpapper = new Imagem();
        ImageIcon wallpaperimage = new ImageIcon(wallpapper.montarCaminho("wallpaper.jpg"));
        lWallpaper = new JLabel();
        lWallpaper.setLocation(50,50);
        lWallpaper.setIcon(wallpaperimage);
        lWallpaper.setHorizontalAlignment(SwingConstants.CENTER);
        this.add (lWallpaper);

        // Barra de Menu
        MenuB = new BarradeMenu();
        MenuB.add(this);
        this.setJMenuBar(MenuB.make());

        this.setSize(800,700);
        this.setTitle("Restaurante da Esquina");
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

    public void buscarCliente() throws SQLException {

        new BuscaCliente();
    }

    public void cadastrarCliente(){
        new TelaCliente();
    }

    public void removerCliente() throws SQLException {
        String id = JOptionPane.showInputDialog("digite o id");
        new DMCliente().apagarCliente(id);
    }

    public void editarCliente() throws SQLException {
        String id = JOptionPane.showInputDialog("digite o id");
        Cliente cliente = new DMCliente().consultarCliente(id);
        Endereco endereco = new DMEndereco().consutlarEndereco(cliente.getIdEndereco());
        Telefone telefone = new DMTelefone().consultarTelefone(cliente.getIdTelefone());
        new TelaAlterarCliente(cliente, endereco, telefone);

    }

    public void cadastrarPedido() throws SQLException {
        new JanelaPedidos();
    }

    public void apagarPedido(){
        JOptionPane.showMessageDialog(null,"REMOVER");
    }

    public void cadastrarProduto(){
        new JanelaCadastrarProduto();
    }

    public void apagarProduto() throws SQLException {
        String id = JOptionPane.showInputDialog("digite o id");
        try{
        new DMProduto().apagarProduto(id);
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso.","DELETADO!", JOptionPane.ERROR_MESSAGE);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void adicionarFuncionario(){
        new JanelaAdicionarFuncionario();
    }

    public void editarFuncionario(){
        JOptionPane.showMessageDialog(null,"CADASTRAR");
    }

    public void apagarFuncionario(){
        String id = JOptionPane.showInputDialog("digite o id");
        try{
            new DMFuncionario().apagarFuncionario(id);
            JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso.","DELETADO!", JOptionPane.ERROR_MESSAGE);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void buscarProduto() throws SQLException {
        new JanelaBuscaProduto();
    }

}
