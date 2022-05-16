package Persistencia;

import Entidades.Estado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Estado> {
    
    @Override
    public void salvar(Estado estado) {
        
        String sql = "INSERT INTO ESTADO "
                + "(DESCRICAO, SIGLA) VALUES "
                + "(?,?);";
        
        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, estado.getDescricao());
            pstm.setString(2, estado.getSigla());
            pstm.execute();
            conexao.commit();
            desconectar();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    @Override
    public void modificar(Estado estado) {
        
        String sql = "UPDATE ESTADO SET"
                + "DESCRICAO = ?, SIGLA = ?"
                + "WHERE IDENTIFICADOR = ?;";
        
        try {
            conectar();
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, estado.getDescricao());
            pstm.setString(2, estado.getSigla());
            pstm.setInt(3, estado.getIdentificador());
            
            pstm.execute();
            
            conexao.commit();
            desconectar();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
    
    @Override
    public void deletar(Estado estado) {
        
        String sql = "DELETE FROM ESTADO "
                + "WHERE IDENTIFICADOR = ?;";
        
        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, estado.getIdentificador());
            pstm.execute();
            conexao.commit();
            desconectar();
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
            
        }
    }
    
    @Override
    public Estado buscarPorId(Integer id) {
        
        Estado estado = new Estado();
        
        String sql = "SELECT * FROM ESTADO "
                + "WHERE IDENTIFICADOR = ?;";
        
        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                estado.setIdentificador(lista.getInt("IDENTIFICADOR"));
                estado.setSigla(lista.getString("SIGLA"));
                estado.setDescricao(lista.getString("DESCRICAO"));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        desconectar();
        return estado;        
    }
    
    @Override
    public List<Estado> listarTodos() {
        
        List<Estado> listaEstado = new ArrayList<Estado>();
        
        String sql = "SELECT * FROM TIPO_TELEFONE";
        
        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            
            ResultSet lista = pstm.executeQuery();
            
            while (lista.next()) {
                Estado estado = new Estado();
                estado.setIdentificador(lista.getInt("IDENTIFICADOR"));
                estado.setDescricao(lista.getString("DESCRICAO"));
                estado.setSigla(lista.getString("SIGLA"));
                listaEstado.add(estado);
            }
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
            
        }
        
        desconectar();
        return listaEstado;
        
    }
    
}
