package Apresentacao;

import Modelo.Pedido;
import Modelo.Produto;
import Persistencia.DMPedido;
import Persistencia.DMProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JanelaPedidos extends JFrame{

    JButton buscar, adicionar, finalizarPedido;
    JLabel lNome, lValor;
    JTextField tNome;
    double valor;
    JPanel painel = new JPanel();
    JPanel painelCarriho = new JPanel();
    Object [][] dados = new Object[][]{
            {}
    };
    Object [][] dadosCarrinho = new Object[][]{
            {}
    };

    JTable tabela = new JTable(new DefaultTableModel(dados,new Object[]{"ID", "NOME","VALOR"}));
    JTable tabelaCarrinho = new JTable(new DefaultTableModel(dadosCarrinho,new Object[]{"Quantidade", "Nome","Valor"}));
    public JanelaPedidos() throws SQLException {

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setNumRows(0);

        DMProduto dmp = new DMProduto();
        for (Produto p : dmp.consultar("")){
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getValor()
            });
        }

        JScrollPane barraRolagem = new JScrollPane();
        barraRolagem.setPreferredSize(new Dimension(800,150));
        barraRolagem.setViewportView(tabela);
        painel.add(barraRolagem);
        this.add(painel);

        painel.setLocation(0,100);
        painel.setSize(800,150);
        painel.setLocation(0,100);

        int yBotoes = 70;


        OuvinteCliente oC = new OuvinteCliente();

        lValor = new JLabel("R$" + this.valor);
        lValor.setSize(300,50);
        Font f = new Font("SansSerif", Font.BOLD, 40);
        lValor.setFont(f);
        lValor.setLocation(500,20);
        this.add(lValor);


        lNome = new JLabel("Nome:");
        lNome.setSize(50,20);
        lNome.setLocation(20,20);
        this.add(lNome);

        tNome = new JTextField();
        tNome.setSize(200,20);
        tNome.setLocation(77,23);
        this.add(tNome);

        buscar = new JButton("Buscar");
        buscar.setSize(100,20);
        buscar.setLocation(320,25);
        buscar.addMouseListener(oC);
        this.add(buscar);

        adicionar = new JButton("Adicionar ao Pedido");
        adicionar.setSize(200,40);
        adicionar.setLocation(320,275);
        adicionar.addMouseListener(oC);
        this.add(adicionar);

        finalizarPedido = new JButton("Finalizar Pedido");
        finalizarPedido.setSize(200,40);
        finalizarPedido.setLocation(320,600);
        finalizarPedido.addMouseListener(oC);
        this.add(finalizarPedido);

        DefaultTableModel modelCarrinho = (DefaultTableModel) tabelaCarrinho.getModel();
        modelCarrinho.setNumRows(0);

        JScrollPane barraRolagemCarrinho = new JScrollPane();
        barraRolagemCarrinho.setPreferredSize(new Dimension(800,150));
        barraRolagemCarrinho.setViewportView(tabelaCarrinho);
        painelCarriho.add(barraRolagemCarrinho);
        this.add(painelCarriho);

        painelCarriho.setSize(800,150);
        painelCarriho.setLocation(0,400);

        this.setSize(800,700);
        this.setTitle("Pedidos");
        this.getContentPane().setBackground(new Color(177, 148, 227));
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        criarJTable();
    }

    public void criarJTable(){

    }

    class OuvinteCliente extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == buscar) {
                String nome = tNome.getText();
                DMProduto dmp = new DMProduto();
                try {
                    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                    model.setNumRows(0);
                    for ( Produto p : dmp.consultar(nome)){
                        model.addRow(new Object[]{
                                p.getId(),
                                p.getNome(),
                                p.getValor()
                        });
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == adicionar) {
                int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade?"));
                String nome = (String) tabela.getValueAt(tabela.getSelectedRow(), 1);
                double valor = (double) tabela.getValueAt(tabela.getSelectedRow(), 2);
                double soma = montarCarrinho(nome,valor,quantidade);
                StringBuilder conteudoLabel = new StringBuilder(lValor.getText());
                conteudoLabel.deleteCharAt(0);
                conteudoLabel.deleteCharAt(0);
                double valorLabel = Double.parseDouble(conteudoLabel.toString());
                lValor.setText("R$" + (valorLabel + soma) );
            }
            if (e.getSource() == finalizarPedido ){
                double soma = 0;
                List<String> nomesProdutos = new ArrayList<>();
                for( int i = 0; i < tabelaCarrinho.getRowCount(); i++){
                    nomesProdutos.add((String) tabelaCarrinho.getValueAt( i, 1));
                    soma += (double) tabelaCarrinho.getValueAt( i, 2);
                }
                Pedido p = new Pedido(0,soma);
                try {
                    int idPedido = new DMPedido().inserir(p);
                    new DMPedido().inserirPedidoProduto(idPedido,nomesProdutos);
                    JOptionPane.showMessageDialog(null, "Seu pedido foi finalizado com sucesso e ficou no valor de " + soma, "Finalizado!", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }


    public double montarCarrinho(String nome, double valor, int quantidade){
        DefaultTableModel modelCarrinho = (DefaultTableModel) tabelaCarrinho.getModel();


        modelCarrinho.addRow(new Object[]{
                        quantidade,
                        nome,
                        valor*quantidade
                });
        return valor*quantidade;
    }

}
