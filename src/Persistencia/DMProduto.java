package Persistencia;

import Modelo.Cliente;
import Modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DMProduto extends DMGeral {

    public void inserirProduto(Produto p) throws SQLException {
        Connection con =  DMGeral.getConnection();
        String sql = "INSERT INTO produto (nome,valor) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, p.getNome());
        stmt.setDouble(2, p.getValor());

        stmt.executeUpdate();
    }

    public void apagarProduto(String id) throws SQLException {
        Connection con = DMGeral.getConnection();
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.executeUpdate();
    }

    public Produto[] consultar(String nomeProduto) throws SQLException {
        Connection con = DMGeral.getConnection();

        String sql = "SELECT * FROM produto WHERE nome like ?";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + nomeProduto + "%");
        ResultSet r = stmt.executeQuery();
        Produto[] produtos = new Produto[getReturnLength(r)];
        ResultSet rs = stmt.executeQuery();

        if (rs.next() == true) {
            int i = 0;
            produtos[0] = new Produto(rs.getString("nome"),rs.getInt("id_produto"), rs.getDouble("valor"));
            i++;
            while (rs.next()) {
                produtos[i] = new Produto(rs.getString("nome"),rs.getInt("id_produto"), rs.getDouble("valor"));
                i++;
            }
        } else {
            System.out.println("Produto não encontrado no Banco de Dados.");
        }
        return produtos;
    }
}
