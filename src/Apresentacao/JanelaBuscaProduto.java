package Apresentacao;

import Modelo.Produto;
import Persistencia.DMProduto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class JanelaBuscaProduto extends JFrame{

    JButton buscar;
    JLabel lNome;
    JTextField tNome;
    JPanel painel = new JPanel();
    Object [][] dados = new Object[][]{
            {}
    };

    JTable tabela = new JTable(new DefaultTableModel(dados,new Object[]{"ID", "NOME","VALOR"}));

    public JanelaBuscaProduto() throws SQLException {

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

        this.setSize(800,300);
        this.setTitle("Cliente");
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

        }

    }
}
