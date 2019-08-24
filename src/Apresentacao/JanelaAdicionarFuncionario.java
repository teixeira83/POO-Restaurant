package Apresentacao;

import Modelo.*;
import Persistencia.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class JanelaAdicionarFuncionario extends JFrame {

    private JTextField tNome, tPass;
    private JLabel lNome, lPass, lBSalvar;
    private JButton bSalvar;

    public JanelaAdicionarFuncionario() {


        OuvinteCliente oC = new OuvinteCliente();

        //Labels
        lNome = new JLabel("Login:");
        lNome.setSize(100, 30);
        lNome.setLocation(30, 50);
        this.add(lNome);

        lPass = new JLabel("Senha:");
        lPass.setSize(100, 30);
        lPass.setLocation(30, 90);
        this.add(lPass);

        //Texts
        tNome = new JTextField();
        tNome.setSize(150, 20);
        tNome.setLocation(80, 55);
        this.add(tNome);

        tPass = new JTextField();
        tPass.setSize(150, 20);
        tPass.setLocation(80, 95);
        this.add(tPass);

        Imagem save = new Imagem();
        ImageIcon iconSalvar = new ImageIcon(save.montarCaminho("save_icon.png"));
        bSalvar = new JButton(iconSalvar);
        bSalvar.setSize(55, 55);
        bSalvar.setLocation(290, 60);
        bSalvar.setForeground(Color.white);
        bSalvar.addMouseListener(oC);
        this.add(bSalvar);

        lBSalvar = new JLabel("Salvar Funcionario");
        lBSalvar.setSize(250, 30);
        lBSalvar.setLocation(270, 110);
        this.add(lBSalvar);

        this.setSize(400, 200);
        this.setTitle("Cadastrar Funcionario");
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    class OuvinteCliente extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == bSalvar) {
                Funcionario f = new Funcionario(tNome.getText(),tPass.getText());
                try {
                    new DMFuncionario().inserirFuncionario(f);
                    JOptionPane.showMessageDialog(null, "Funcionario inserido com sucesso");
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }
}