package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ConexaoComOBancoDeDados {

    private final String urlDoBanco
            = "jdbc:hsqldb:file:BancoDeDados/AgendaMedicaBD;hsqldb.lock_file=false";

    private final String usuarioDoBanco = "SA";

    private final String senhaDoBanco = "";

    Connection conexao;

    public Connection conectar() {

        try {
            conexao = DriverManager.getConnection(urlDoBanco, usuarioDoBanco, senhaDoBanco);
        } catch (SQLException ex) {

            System.out.println(ex);
        }

        return conexao;
    }

    public void desconectar() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void executarScriptSql(String sql) {

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.execute();
            conexao.commit();
            System.out.println("Script executado com sucesso --> " + sql);
            System.out.println("");
        } catch (SQLException ex) {
            System.out.println("");
            System.out.println("#############################################");
            System.out.println(ex);
            System.out.println("");
            System.out.println("Script com erro --> " + sql);
            System.out.println("#############################################");
        }

        desconectar();

    }

    public boolean iSTabelaVazia(String tabela) {

        String sql = "SELECT * FROM " + tabela;
        boolean isVazia = true;

        try {
            conectar();
            PreparedStatement pstm
                    = conexao.prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            isVazia = !lista.next();
            System.out.println("executando Script --> " + sql);
            if (isVazia) {
                System.out.println("Tabela " + tabela + " vazia!");
            } else {
                System.out.println("TABELA " + tabela + " COM DADOS INICIAIS JÁ CADASTRADOS! ");
            }
            System.out.println("");

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        desconectar();
        return isVazia;
    }

    public int ultimoIdentificadorDaTabela(String tabela) {

        String sql = "SELECT MAX(IDENTIFICADOR) AS ID FROM  " + tabela;
        int ultimoId = 0;

        try {
            conectar();
            PreparedStatement pstm
                    = conexao.prepareStatement(sql);

            ResultSet lista = pstm.executeQuery();

            while (lista.next()) {

                ultimoId = lista.getInt("ID");

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        desconectar();
        return ultimoId;
    }

}
