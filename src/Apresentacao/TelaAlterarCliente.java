package Apresentacao;

import Modelo.*;
import Persistencia.DMCliente;
import Persistencia.DMEndereco;
import Persistencia.DMGeral;
import Persistencia.DMTelefone;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;


@SuppressWarnings("serial")
public class TelaAlterarCliente extends JFrame {


    private JLabel lDados, lEndereco, lContato, lNome, lCpf, lRua, lNumero, lComplemento, lBairro, lCidade, lCep, lEstado, lDdd, lTel, lOperadora, lBLimpar, lBSalvar, lCliente, lAdress, lIconeContato;
    private JTextField tNome, tCpf, tRua, tNumero, tComplemento, tBairro, tCidade, tCep, tEstado, tDdd, tTel, tOperadora;
    private JButton bSalvar, bLimpar;
    //variaveis locais para armazenar o que for digitado
    String cpf, nome, rua, numero, complemento, bairro, cidade, cep, estado, ddd, tel, operadora;

    Cliente cliente;
    Endereco endereco;
    Telefone telefone;

    public TelaAlterarCliente(Cliente cliente, Endereco endereco, Telefone telefone) {

        this.cliente = cliente;
        this.endereco = endereco;
        this.telefone = telefone;

        Imagem imagemCliente = new Imagem();
        ImageIcon iconCliente = new ImageIcon(imagemCliente.montarCaminho("cliente_icon.png"));
        lCliente = new JLabel();
        lCliente.setSize(50, 50);
        lCliente.setLocation(15, 10);
        lCliente.setIcon(iconCliente);
        this.add(lCliente);

        lDados = new JLabel("");
        lDados.setText("<html><u>DADOS PESSOAIS</u>");
        lDados.setSize(250, 30);
        lDados.setLocation(70, 35);
        this.add(lDados);

        lCpf = new JLabel("CPF:");
        lCpf.setSize(50, 30);
        lCpf.setLocation(20, 60);
        this.add(lCpf);

        tCpf = new JTextField(cliente.getCpf());
        tCpf.setSize(150, 20);
        tCpf.setLocation(80, 65);
        this.add(tCpf);

        lNome = new JLabel("Nome: ");
        lNome.setSize(120, 30);
        lNome.setLocation(250, 60);
        this.add(lNome);

        tNome = new JTextField(cliente.getNome());
        tNome.setSize(460, 20);
        tNome.setLocation(320, 65);
        this.add(tNome);


        Imagem imagemEndereco = new Imagem();
        ImageIcon iconEndereco = new ImageIcon(imagemEndereco.montarCaminho("adress_icon.png"));
        lAdress = new JLabel();
        lAdress.setSize(50, 50);
        lAdress.setLocation(15, 100);
        lAdress.setIcon(iconEndereco);
        this.add(lAdress);


        lEndereco = new JLabel();
        lEndereco.setText("<html><u>ENDEREÇO</u>");
        lEndereco.setSize(250, 30);
        lEndereco.setLocation(70, 115);
        this.add(lEndereco);


        //endere�o
        lRua = new JLabel("Logradouro:");
        lRua.setSize(100, 30);
        lRua.setLocation(20, 150);
        this.add(lRua);

        tRua = new JTextField(endereco.getLogradouro());
        tRua.setSize(400, 20);
        tRua.setLocation(110, 155);
        this.add(tRua);

        lNumero = new JLabel("Número:");
        lNumero.setSize(120, 30);
        lNumero.setLocation(550, 150);
        this.add(lNumero);

        tNumero = new JTextField(endereco.getNum());
        tNumero.setSize(100, 20);
        tNumero.setLocation(625, 155);
        this.add(tNumero);

        lComplemento = new JLabel("Complemento:");
        lComplemento.setSize(120, 30);
        lComplemento.setLocation(20, 180);
        this.add(lComplemento);

        tComplemento = new JTextField(endereco.getComplemento());
        tComplemento.setSize(150, 20);
        tComplemento.setLocation(130, 185);
        this.add(tComplemento);

        lBairro = new JLabel("Bairro: ");
        lBairro.setSize(120, 30);
        lBairro.setLocation(300, 180);
        this.add(lBairro);

        tBairro = new JTextField(endereco.getBairro());
        tBairro.setSize(220, 20);
        tBairro.setLocation(350, 185);
        this.add(tBairro);

        lCep = new JLabel("CEP:");
        lCep.setSize(60, 30);
        lCep.setLocation(20, 215);
        this.add(lCep);

        tCep = new JTextField(endereco.getCep());
        tCep.setSize(160, 20);
        tCep.setLocation(70, 220);
        this.add(tCep);

        lCidade = new JLabel("Cidade: ");
        lCidade.setSize(120, 30);
        lCidade.setLocation(250, 215);
        this.add(lCidade);

        tCidade = new JTextField(endereco.getCidade());
        tCidade.setSize(230, 20);
        tCidade.setLocation(320, 220);
        this.add(tCidade);

        lEstado = new JLabel("Estado:");
        lEstado.setSize(120, 30);
        lEstado.setLocation(580, 215);
        this.add(lEstado);

        tEstado = new JTextField(endereco.getEstado());
        tEstado.setSize(80, 20);
        tEstado.setLocation(635, 220);
        this.add(tEstado);


        Imagem contato = new Imagem();
        ImageIcon iconContato = new ImageIcon(contato.montarCaminho("contat_icon.png"));
        lIconeContato = new JLabel();
        lIconeContato.setSize(50, 50);
        lIconeContato.setLocation(15, 260);
        lIconeContato.setIcon(iconContato);
        this.add(lIconeContato);


        lContato = new JLabel();
        lContato.setText("<html><u>CONTATO</u>");
        lContato.setSize(250, 30);
        lContato.setLocation(70, 280);
        this.add(lContato);

        lDdd = new JLabel("DDD:");
        lDdd.setSize(120, 30);
        lDdd.setLocation(20, 305);
        this.add(lDdd);

        tDdd = new JTextField(telefone.getDdd());
        tDdd.setSize(50, 20);
        tDdd.setLocation(70, 310);
        this.add(tDdd);

        lTel = new JLabel("Número: ");
        lTel.setSize(80, 30);
        lTel.setLocation(150, 305);
        this.add(lTel);

        tTel = new JTextField(telefone.getNum());
        tTel.setSize(140, 20);
        tTel.setLocation(220, 310);
        this.add(tTel);

        lOperadora = new JLabel("Operadora: ");
        lOperadora.setSize(150, 30);
        lOperadora.setLocation(370, 305);
        this.add(lOperadora);

        tOperadora = new JTextField(telefone.getOperadora());
        tOperadora.setSize(180, 20);
        tOperadora.setLocation(460, 310);
        this.add(tOperadora);



        OuvinteCliente oC = new OuvinteCliente();

        Imagem save = new Imagem();
        ImageIcon iconSalvar = new ImageIcon(save.montarCaminho("save_icon.png"));
        bSalvar = new JButton(iconSalvar);
        bSalvar.setSize(55, 55);
        bSalvar.setLocation(150, 350);
        bSalvar.setForeground(Color.white);
        bSalvar.addMouseListener(oC);
        this.add(bSalvar);

        lBSalvar = new JLabel("Salvar");
        lBSalvar.setSize(250, 30);
        lBSalvar.setLocation(155, 400);
        this.add(lBSalvar);


        Imagem trash = new Imagem();
        ImageIcon iconTrash = new ImageIcon(trash.montarCaminho("trash_icon.png"));
        bLimpar = new JButton(iconTrash);
        bLimpar.setSize(55, 55);
        bLimpar.setLocation(500, 350);
        bLimpar.setForeground(Color.white);
        bLimpar.addMouseListener(oC);
        this.add(bLimpar);

        lBLimpar = new JLabel("Limpar");
        lBLimpar.setSize(250, 30);
        lBLimpar.setLocation(505, 400);
        this.add(lBLimpar);

        this.setSize(800, 470);
        this.setTitle("Cliente");
        this.getContentPane().setBackground(new Color(177, 148, 227));
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    class OuvinteCliente extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == bSalvar) {

                endereco.setLogradouro(tRua.getText());
                endereco.setNum(tNumero.getText());
                endereco.setComplemento(tComplemento.getText());
                endereco.setBairro(bairro = tBairro.getText());
                endereco.setCep(tCep.getText());
                endereco.setCidade(tCidade.getText());
                endereco.setEstado(tEstado.getText());

                try {
                    idEndereco = new DMEndereco().incluirEndereco(end);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                ddd = tDdd.getText();
                tel = tTel.getText();
                operadora = tOperadora.getText();

                Telefone telefone = new Telefone(0, ddd, tel, operadora);

                int idTelefone = 0 ;

                try {
                    idTelefone = new DMTelefone().inserirTelefone(telefone);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                cpf = tCpf.getText();
                nome = tNome.getText();
                Cliente c = new Cliente(0,nome, cpf, idTelefone, idEndereco);

                try {
                    new DMCliente().inserirCliente(c);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }


        }

    }

}