
package Persistencia;

import Entidades.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Cargo>{

    @Override
    public void salvar(Cargo cargo) {
        
       String sql = "INSERT INTO CARGO "
                + "(DESCRICAO, ATIVO) VALUES "
                + "(?,?);";

        try {
            conectar();
            PreparedStatement pstm  = conexao.prepareStatement(sql);
            pstm.setString(1, cargo.getDescricao());
            pstm.setBoolean(2, cargo.isAtivo());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void modificar(Cargo cargo) {
       
        String sql = "UPDATE CARGO SET "
                + "DESCRICAO = ? , ATIVO = ? "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, cargo.getDescricao());
            pstm.setBoolean(2, cargo.isAtivo());

            pstm.execute();

            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deletar(Cargo cargo) {
        
        
        String sql = "DELETE FROM CARGO WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);

            pstm.setInt(1, cargo.getIdentificador());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }
        
    }

    @Override
    public Cargo buscarPorId(Integer id) {
        
       Cargo cargo = new Cargo();

        String sql = "SELECT * FROM CARGO "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                cargo.setIdentificador(lista.getInt("IDENTIFICADOR"));
                cargo.setDescricao(lista.getString("DESCRICAO"));
                cargo.setAtivo(true);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        desconectar();
        return cargo;
    }

    @Override
    public List<Cargo> listarTodos() {
        
         List<Cargo> listaDeCargos = new ArrayList<Cargo>();

        String sql = "SELECT * FROM CARGO";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);

            ResultSet lista = pstm.executeQuery();

            while (lista.next()) {
                Cargo cargo = new Cargo();
                cargo.setIdentificador(lista.getInt("IDENTIFICADOR"));
                cargo.setDescricao(lista.getString("DESCRICAO"));
                cargo.setAtivo(true);
                listaDeCargos.add(cargo);
            }

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        desconectar();
        return listaDeCargos;
        
    }
    
}
