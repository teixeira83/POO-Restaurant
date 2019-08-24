package Persistencia;


import Modelo.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DMFuncionario extends DMGeral{


    public void inserirFuncionario(Funcionario f) throws SQLException {
        Connection con =  DMGeral.getConnection();
        String sql = "INSERT INTO funcionario (login,senha) values (?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, f.getLogin());
        stmt.setString(2, f.getPassword());

        stmt.executeUpdate();
    }

    public void apagarFuncionario(String id) throws SQLException {
        Connection con = DMGeral.getConnection();
        String sql = "DELETE FROM funcionario WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.executeUpdate();
    }

}
