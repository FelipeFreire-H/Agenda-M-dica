package Persistencia;

import Entidades.Endereco;
import Entidades.Telefone;
import Entidades.Usuario;
import Util.DataUtility;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Usuario> {

    PerfilDAO daoPerfil = new PerfilDAO();
    
    @Override
    public void salvar(Usuario usuario) {

        String sqlUsuario = "INSERT INTO USUARIO "
                + "(NOME, CPF_CNPJ, SEXO, DATA_NASCIMENTO, USERNAME, SENHA, ATIVO, IDENTIFICADOR_PERFIL, DATA_CADASTRO) VALUES "
                + "(?,?,?,?,?,?,?,?,?);";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sqlUsuario);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getCpfCnpj());
            /*
            O atributo sexo é do tipo char.
            Faz-se necessário converter char para String
             */
            pstm.setString(3, Character.toString(usuario.getSexo()));
            //Faz-se necessário converter Date.util para Date.sql
            pstm.setDate(4, DataUtility.dateParaDateSql(usuario.getDataNascimento()));
            pstm.setString(5, usuario.getUserName());
            //Faz-se necessário criptografar a senha
            pstm.setString(6,BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt(11)));
            pstm.setBoolean(7, usuario.isAtivo());
            pstm.setInt(8, usuario.getPerfil().getIdentificador());
            //Faz-se necessário converter Date.util para Date.sql
            pstm.setDate(9, DataUtility.dateParaDateSql(new Date()));
            pstm.execute();
            conexao.commit();
            desconectar();

            //Script sql para inserir os telefones do usuário, caso tenha.
            String sqlUsuarioTelefone = "INSERT INTO USUARIO_TELEFONE "
                    + "(IDENTIFICADOR_USUARIO, IDENTIFICADOR_TELEFONE) VALUES "
                    + "(?,?);";

            //Script sql para inserir os endereços do usuário, caso tenha.
            String sqlUsuarioEndereco = "INSERT INTO USUARIO_ENDERECO "
                    + "(IDENTIFICADOR_USUARIO, IDENTIFICADOR_ENDERECO) VALUES "
                    + "(?,?);";
            
            int idUsuario = ultimoIdentificadorDaTabela("USUARIO");

            // verifica se a lista de telefone não está vazia
            if (!usuario.getListaTelefone().isEmpty()) {
            //para cada telefone encontrado na lista abre conexão
                for (Telefone telefone : usuario.getListaTelefone()) {
                    conectar();
                    PreparedStatement pstmTelefone = conexao.prepareStatement(sqlUsuarioTelefone);
                    pstmTelefone.setInt(1, idUsuario);
                    pstmTelefone.setInt(2, telefone.getIdentificador());
                    pstmTelefone.execute();
                    conexao.commit();
                    desconectar();
                }

            }

            // verifica se a lista de endereço não está vazia
            if (!usuario.getListaEndereco().isEmpty()) {

                for (Endereco endereco : usuario.getListaEndereco()) {
                    conectar();
                    PreparedStatement pstmEndereco = conexao.prepareStatement(sqlUsuarioEndereco);
                    pstmEndereco.setInt(1, idUsuario);
                    pstmEndereco.setInt(2, endereco.getIdentificador());
                    pstmEndereco.execute();
                    conexao.commit();
                    desconectar();

                }

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void modificar(Usuario usuario) {  
        
        String sql = "UPDATE USUARIO SET USERNAME = ?, SENHA = ?, ATIVO = ?, IDENIFICADOR_PERFIL = ?, DATACADASTRO = ?,"
                + "NOME = ?, CPF_CNPJ = ?, SEXO = ?, DATA_NASCIMENTO = ?"
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuario.getUserName());
            //criptografado
            pstm.setString(2, BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt(11)));
            pstm.setBoolean(3, usuario.isAtivo());
            pstm.setInt(4, usuario.getPerfil().getIdentificador());
            java.sql.Date dataPadraoSqlCad = new java.sql.Date(usuario.getDataCadastro().getTime());
            pstm.setDate(5, dataPadraoSqlCad);
            //------------------------------------------------------------------------------------
            pstm.setString(6, usuario.getNome());
            pstm.setString(7, usuario.getCpfCnpj());
            pstm.setString(8, Character.toString(usuario.getSexo()));         
            java.sql.Date dataPadraoSqlNasc = new java.sql.Date(usuario.getDataNascimento().getTime());
            pstm.setDate(9, dataPadraoSqlNasc);
            
            pstm.execute();
            conexao.commit();
            desconectar();
            
            //Script sql para atualizar os telefones do usuário, caso tenha.
            String sqlUsuarioTelefone = "UPDATE USUARIO SET USUARIO_TELEFONE "
                    + "(IDENTIFICADOR_USUARIO, IDENTIFICADOR_TELEFONE) VALUES "
                    + "(?,?);";

            //Script sql para atualizar os endereços do usuário, caso tenha.
            String sqlUsuarioEndereco = "UPDATE USUARIO SET USUARIO_ENDERECO "
                    + "(IDENTIFICADOR_USUARIO, IDENTIFICADOR_ENDERECO) VALUES "
                    + "(?,?);";
            
            int idUsuario = ultimoIdentificadorDaTabela("USUARIO");

            // verifica se a lista de telefone não está vazia
            if (!usuario.getListaTelefone().isEmpty()) {
            //para cada telefone encontrado na lista abre conexão
                for (Telefone telefone : usuario.getListaTelefone()) {
                    conectar();
                    PreparedStatement pstmTelefone = conexao.prepareStatement(sqlUsuarioTelefone);
                    pstmTelefone.setInt(1, idUsuario);
                    pstmTelefone.setInt(2, telefone.getIdentificador());
                    pstmTelefone.execute();
                    conexao.commit();
                    desconectar();
                }

            }

            // verifica se a lista de endereço não está vazia
            if (!usuario.getListaEndereco().isEmpty()) {

                for (Endereco endereco : usuario.getListaEndereco()) {
                    conectar();
                    PreparedStatement pstmEndereco = conexao.prepareStatement(sqlUsuarioEndereco);
                    pstmEndereco.setInt(1, idUsuario);
                    pstmEndereco.setInt(2, endereco.getIdentificador());
                    pstmEndereco.execute();
                    conexao.commit();
                    desconectar();

                }

            }
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }     
    }

    @Override
    public void deletar(Usuario usuario) {
       
         String sql = "DELETE FROM USUARIO WHERE IDENTIFICADOR = ?;";
         
        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, usuario.getIdentificador());
            pstm.execute();
            conexao.commit();
            desconectar();
              
        } catch (SQLException ex) {
            System.out.println(ex);
        }  
    }

    @Override
    public Usuario buscarPorId(Integer id) {
            
        Usuario usuario = new Usuario();

        String sql = "SELECT * FROM USUARIO "
                + "WHERE IDENTIFICADOR = ?;";

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, id);

            ResultSet lista = pstm.executeQuery();
            while (lista.next()) {
                usuario.setIdentificador(lista.getInt("IDENTIFICADOR"));
                usuario.setNome(lista.getString("NOME"));
                usuario.setUserName(lista.getString("USERNAME"));
                usuario.setSexo(lista.getString("SEXO").charAt(0));
                usuario.setAtivo(true);
                usuario.setCpfCnpj(lista.getString("CPF_CNPJ"));
                usuario.setDataCadastro(lista.getDate("DATA_CADASTRO"));
                usuario.setDataNascimento(lista.getDate("DATA_NASCIMENTO"));
                usuario.setSenha(lista.getString("SENHA"));
                //usuario.setListaEndereco();
                //usuario.setListaTelefone();
                usuario.setPerfil(daoPerfil.buscarPorId(lista.getInt("IDENTIFICADOR_PERFIL")));     
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        desconectar();
        return usuario;
        
    }

    @Override
    public List<Usuario> listarTodos() {
       
        //comando devolve todos os perfis cadastrados na tabela perfil
        String sql = "SELECT*FROM PERFIL";

        List<Usuario> listaDePerfis = new ArrayList<Usuario>();

        try {

            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);

            ResultSet resultado = pstm.executeQuery();
            //enquanto existir próximo
            while (resultado.next()) {
                Usuario usuario = new Usuario();

                listaDePerfis.add(usuario);
            }
            desconectar();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listaDePerfis;
        
        
    }

}
