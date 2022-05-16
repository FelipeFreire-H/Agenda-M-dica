/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package PersistenciaTeste;

import Entidades.Endereco;
import Entidades.Perfil;
import Entidades.Telefone;
import Entidades.Usuario;
import Persistencia.PerfilDAO;
import Persistencia.UsuarioDAO;
import Util.DataUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteUsuarioDAO {

    private static UsuarioDAO daoUsuario;
    private static PerfilDAO daoPerfil;
    private static Usuario usuario;
    private static Perfil perfil;
    private static int ultimoIdentificador;
    private static List<Telefone> listaTelefone;
    private static List<Endereco> listaEndereco;

    @BeforeClass
    public static void setUpClass() {

        daoUsuario = new UsuarioDAO();
        daoPerfil = new PerfilDAO();
        perfil = daoPerfil.buscarPorId(101);
        ultimoIdentificador = daoUsuario.ultimoIdentificadorDaTabela("USUARIO");
        listaEndereco = new ArrayList<>();
        listaTelefone = new ArrayList<>();
        usuario = new Usuario();
        usuario.setCpfCnpj("8654352662");
        usuario.setNome("USUÁRIO DE TESTE");
        usuario.setSexo('M');
        usuario.setDataNascimento(DataUtility.transformarStringEmDate("16/08/1978"));
        usuario.setUserName("testeUser");
        usuario.setAtivo(true);
        usuario.setDataCadastro(new Date());
        usuario.setPerfil(perfil);
        usuario.setListaEndereco(listaEndereco);
        usuario.setListaTelefone(listaTelefone);

    }

    @Test
    public void testeSalvarUsuario() {

        daoUsuario.salvar(usuario);
        assertEquals(ultimoIdentificador + 1, daoUsuario.ultimoIdentificadorDaTabela("USUARIO"));

    }

    @AfterClass
    public static void tearDownClass() {
    }

}
