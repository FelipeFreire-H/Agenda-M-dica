
package Persistencia;

import Entidades.Funcionario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Funcionario>{

    @Override
    public void salvar(Funcionario funcionario) {
      
            String sql = "INSERT INTO FUNCIONARIO "
                + "(IDENTIFICADOR_CARGO, DATA_ADMISSAO, IDENTIFICADOR_USUARIO, DATA_DESLIGAMENTO ) VALUES "
                + "(?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm  = conexao.prepareStatement(sql);
            pstm.setInt(1, funcionario.getCargo().getIdentificador());
            java.sql.Date dataPadraoSqlAdmissao = new java.sql.Date(funcionario.getDataAdmissao().getTime());
            pstm.setDate(2, dataPadraoSqlAdmissao);
            pstm.setInt(3, funcionario.getUsuario().getIdentificador());
            java.sql.Date dataPadraoSqlDesligamento = new java.sql.Date(funcionario.getDataDesligamento().getTime());
            pstm.setDate(4, dataPadraoSqlDesligamento);
            
            
            
            pstm.execute();
            conexao.commit();
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    @Override
    public void modificar(Funcionario entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Funcionario entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario buscarPorId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Funcionario> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
