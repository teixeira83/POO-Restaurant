package Persistencia;

import Modelo.Pedido;

import java.sql.*;
import java.util.List;

public class DMPedido extends DMGeral{

    public int inserir(Pedido p) throws SQLException{
        Connection con = DMGeral.getConnection();

        String sql = "INSERT INTO pedido (valor_total) values (?)";
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setDouble(1, p.getValorTotal());

        Integer idPedido = 0;
        if (stmt.executeUpdate() > 0) {

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            idPedido = rs.getInt(1);
        }
        stmt.close();

        return idPedido;
    }

    public void inserirPedidoProduto(int idPedido,List<String> nomesProdutos) throws SQLException {
        for( int i = 0; i < nomesProdutos.size(); i++){
            int id = new DMProduto().consultarIdProduto(nomesProdutos.get(i));
            Connection con = DMGeral.getConnection();

            String sql = "INSERT INTO produto_pedido (id_pedido, id_produto) values (?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }


}
