package Apresentacao;

import Modelo.*;
import Persistencia.DMCliente;
import Persistencia.DMEndereco;
import Persistencia.DMProduto;
import Persistencia.DMTelefone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class JanelaCadastrarProduto extends JFrame {

    private JTextField tNome, tValor;
    private JLabel lNome, lValor, lBSalvar;
    private JButton bSalvar;

    public JanelaCadastrarProduto() {


        OuvinteCliente oC = new OuvinteCliente();

        //Labels
        lNome = new JLabel("Nome:");
        lNome.setSize(100, 30);
        lNome.setLocation(30, 50);
        this.add(lNome);

        lValor = new JLabel("Valor:");
        lValor.setSize(100, 30);
        lValor.setLocation(30, 90);
        this.add(lValor);

        //Texts
        tNome = new JTextField();
        tNome.setSize(150, 20);
        tNome.setLocation(80, 55);
        this.add(tNome);

        tValor = new JTextField();
        tValor.setSize(150, 20);
        tValor.setLocation(80, 95);
        this.add(tValor);

        Imagem save = new Imagem();
        ImageIcon iconSalvar = new ImageIcon(save.montarCaminho("save_icon.png"));
        bSalvar = new JButton(iconSalvar);
        bSalvar.setSize(55, 55);
        bSalvar.setLocation(290, 60);
        bSalvar.setForeground(Color.white);
        bSalvar.addMouseListener(oC);
        this.add(bSalvar);

        lBSalvar = new JLabel("Salvar Produto");
        lBSalvar.setSize(250, 30);
        lBSalvar.setLocation(270, 110);
        this.add(lBSalvar);

        this.setSize(400, 200);
        this.setTitle("Cadastrar Produto");
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    class OuvinteCliente extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == bSalvar) {
                Produto p = new Produto(tNome.getText(),0,Double.parseDouble(tValor.getText()));
                try {
                    new DMProduto().inserirProduto(p);
                    JOptionPane.showMessageDialog(null, "Produto inserido com sucesso");
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}