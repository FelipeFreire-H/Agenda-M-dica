
package Persistencia;

import Entidades.TipoEndereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoEnderecoDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, TipoEndereco>{

    @Override
    public void salvar(TipoEndereco tipoEndereco) {
    
         String sql = "INSERT INTO TIPO_ENDERECO "
                + "(DESCRICAO) VALUES "
                + "(?);";

        try {
            conectar();
            PreparedStatement pstm  = conexao.prepareStatement(sql);
            pstm.setString(1, tipoEndereco.getDescricao());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    @Override
    public void modificar(TipoEndereco tipoEndereco) {
        
        String sql = "UPDATE TIPO_ENDERECO SET "
                + "DESCRICAO = ? "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, tipoEndereco.getDescricao());
            pstm.setInt(2, tipoEndereco.getIdentificador());

            pstm.execute();

            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deletar(TipoEndereco tipoEndereco) {
       
        
        String sql = "DELETE FROM TIPO_ENDERECO "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, tipoEndereco.getIdentificador());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }
        
    }

    @Override
    public TipoEndereco buscarPorId(Integer id) {
      
        TipoEndereco tipoTelefone = new TipoEndereco();

        String sql = "SELECT * FROM TIPO_TELEFONE "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                tipoTelefone.setIdentificador(lista.getInt("IDENTIFICADOR"));
                tipoTelefone.setDescricao(lista.getString("DESCRICAO"));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        desconectar();
        return tipoTelefone;
        
    }

    @Override
    public List<TipoEndereco> listarTodos() {
       
        List<TipoEndereco> listaDeTiposDeEndereco = new ArrayList<TipoEndereco>();

        String sql = "SELECT * FROM TIPO_TELEFONE";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);

            ResultSet lista = pstm.executeQuery();

            while (lista.next()) {
                TipoEndereco tipoEndereco = new TipoEndereco();
                tipoEndereco.setIdentificador(lista.getInt("IDENTIFICADOR"));
                tipoEndereco.setDescricao(lista.getString("DESCRICAO"));
                listaDeTiposDeEndereco.add(tipoEndereco);
            }

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        desconectar();
        return listaDeTiposDeEndereco;

    }
    
}
