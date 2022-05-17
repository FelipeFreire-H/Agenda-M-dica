
package Persistencia;

import Entidades.TipoTelefone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoTelefoneDAO  extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, TipoTelefone>{

    @Override
    public void salvar(TipoTelefone tipoTelefone) {
    
         String sql = "INSERT INTO TIPO_TELEFONE "
                + "(DESCRICAO) VALUES "
                 //tipoTelefone.getDescricao();
                + "(?);";

        try {
            conectar();
            PreparedStatement pstm  = conexao.prepareStatement(sql);
            pstm.setString(1, tipoTelefone.getDescricao());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    // TipoTelefona é um TIPO , enquanto tipoTelefone é um atributo
    // where restrição
    @Override
    public void modificar(TipoTelefone tipoTelefone) {
        
        String sql = "UPDATE TIPO_TELEFONE SET "
                + "DESCRICAO = ? "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, tipoTelefone.getDescricao());
            pstm.setInt(2, tipoTelefone.getIdentificador());

            pstm.execute();

            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deletar(TipoTelefone tipoTelefone) {
       
        
        String sql = "DELETE FROM TIPO_TELEFONE"
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, tipoTelefone.getIdentificador());
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {

            System.out.println(ex);

        }
        
    }

    @Override
    public TipoTelefone buscarPorId(Integer id) {
      
        TipoTelefone tipoTelefone = new TipoTelefone();

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
    public List<TipoTelefone> listarTodos() {
       //List é uma interface, por isso é necessário passar uma classe concreta que nesse caso é o ArrayList
        List<TipoTelefone> listaDeTiposDeTelefone = new ArrayList<TipoTelefone>();

        String sql = "SELECT * FROM TIPO_TELEFONE";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            
            ResultSet lista = pstm.executeQuery();

            while (lista.next()) {
                TipoTelefone tipoTelefone = new TipoTelefone();
                tipoTelefone.setIdentificador(lista.getInt("IDENTIFICADOR"));
                tipoTelefone.setDescricao(lista.getString("DESCRICAO"));
                listaDeTiposDeTelefone.add(tipoTelefone);
            }

        } catch (SQLException ex) {

            System.out.println(ex);

        }

        desconectar();
        return listaDeTiposDeTelefone;

        
    }
    
}
