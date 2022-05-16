package Persistencia;

import Entidades.Perfil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Perfil> {

    @Override
    public void salvar(Perfil perfil) {

        String sql = "INSERT INTO PERFIL "
                + "(DESCRICAO) VALUES "
                + "(?);";

        try {
            conectar();
            PreparedStatement pstm
                    = conexao.prepareStatement(sql);
            pstm.setString(1, perfil.getDescricao());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void modificar(Perfil perfil) {

        String sql = "UPDATE PERFIL SET "
                + "DESCRICAO = ? "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, perfil.getDescricao());
            pstm.setInt(2, perfil.getIdentificador());

            pstm.execute();

            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deletar(Perfil perfil) {

        String sql = "DELETE FROM PERFIL "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm
                    = conexao.prepareStatement(sql);

            pstm.setInt(1, perfil.getIdentificador());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }

    }

    @Override
    public Perfil buscarPorId(Integer id) {

        Perfil perfil = new Perfil();

        String sql = "SELECT * FROM PERFIL "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm
                    = conexao.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                perfil.setIdentificador(lista.getInt("IDENTIFICADOR"));
                perfil.setDescricao(lista.getString("DESCRICAO"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        desconectar();
        return perfil;
    }

    @Override
    public List<Perfil> listarTodos() {

        List<Perfil> listaDePerfis = new ArrayList<Perfil>();

        String sql = "SELECT * FROM PERFIL";

        try {
            conectar();
            PreparedStatement pstm
                    = conexao.prepareStatement(sql);

            ResultSet lista = pstm.executeQuery();

            while (lista.next()) {
                Perfil perfil = new Perfil();
                perfil.setIdentificador(lista.getInt("IDENTIFICADOR"));
                perfil.setDescricao(lista.getString("DESCRICAO"));
                listaDePerfis.add(perfil);
            }

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        desconectar();
        return listaDePerfis;

    }

}
