
package PersistenciaTeste;

import Entidades.Perfil;
import Persistencia.PerfilDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePerfilDAO {

    private static PerfilDAO daoPerfil;
    private static Perfil perfil;
    private static int ultimoIdentificador;
    private static int numeroDePerfisCadastrados;

    public TestePerfilDAO() {
    }

    @BeforeClass
    public static void setUpClass() {

        daoPerfil = new PerfilDAO();
        perfil = new Perfil();
        perfil.setDescricao("PERFIL DE TESTE");       
        ultimoIdentificador = daoPerfil.ultimoIdentificadorDaTabela("PERFIL");
        numeroDePerfisCadastrados = daoPerfil.listarTodos().size();

    }

    @Test
    public void testePerfisIniciais() {
        // número de perfis iniciais (mínimo 03)    
        assertTrue(numeroDePerfisCadastrados >= 3);

        /* TEM QUE TER OS PERFIS INICIAIS   
        101 ADMINISTRADOR 
        102 DIRETOR	 
        103 ATENDENTE	 
         */
        assertEquals("ADMINISTRADOR", daoPerfil.buscarPorId(101).getDescricao());
        assertEquals("DIRETOR", daoPerfil.buscarPorId(102).getDescricao());
        assertEquals("ATENDENTE", daoPerfil.buscarPorId(103).getDescricao());

    }

    @Test
    public void testeBuscarPorId() {

        Perfil perfilTesteBuscarPorId =  daoPerfil.buscarPorId(101);
        assertNotNull(perfilTesteBuscarPorId.getDescricao());
        assertEquals(101, perfilTesteBuscarPorId.getIdentificador());
        assertEquals("ADMINISTRADOR", perfilTesteBuscarPorId.getDescricao());
    }

    @Test
    public void testeSalvarPerfil() {

        // invocando o método da DAO para inserir no banco o perfil de teste 
        daoPerfil.salvar(perfil);

        // verificando se o número de perfis exsitentes no banco aumentou em uma unidade  
        assertEquals(numeroDePerfisCadastrados + 1, daoPerfil.listarTodos().size());

        // Verificando se existe o perfil de teste no banco
        for (Perfil perfilDoBD : daoPerfil.listarTodos()) {

            if (perfilDoBD.getDescricao().equals("PERFIL DE TESTE")) {
                perfil = perfilDoBD;
            }
        }

        // Verificando se existe o perfil de teste no banco
        assertTrue(daoPerfil.buscarPorId(perfil.getIdentificador()).getIdentificador() > ultimoIdentificador);

        // verificando se o perfil de teste não é núlo no banco
        assertNotNull(daoPerfil.buscarPorId(perfil.getIdentificador()).getDescricao());

    }

    @Test
    public void testeUpdatePerfil() {

        // modificando o perfil em memória        
        perfil.setDescricao("PERFIL DE TESTE - MODIFICADO");

        // invocando o método da DAO para modificar o perfil no banco
        daoPerfil.modificar(perfil);

        // Verificando se o perfil foi modificado no Banco
        assertEquals("PERFIL DE TESTE - MODIFICADO", daoPerfil.buscarPorId(perfil.getIdentificador()).getDescricao());

        // voltar perfil para original
        perfil.setDescricao("PERFIL DE TESTE");

        daoPerfil.modificar(perfil);

        assertEquals("PERFIL DE TESTE", daoPerfil.buscarPorId(perfil.getIdentificador()).getDescricao());

    }

    @Test
    public void testeDeletarPerfil() {

        
        numeroDePerfisCadastrados = daoPerfil.listarTodos().size();
        
        // invocando o método da DAO para deletar o perfil de teste      
        daoPerfil.deletar(perfil);

        // verificando se o número de perfis exsitentes no banco reduziu em uma unidade 
        assertEquals(numeroDePerfisCadastrados - 1, daoPerfil.listarTodos().size());

        // verificando se o perfil de teste é núlo no banco
        assertNull(daoPerfil.buscarPorId(perfil.getIdentificador()).getDescricao());

    }

    @AfterClass
    public static void tearDownClass() {
    }

}
