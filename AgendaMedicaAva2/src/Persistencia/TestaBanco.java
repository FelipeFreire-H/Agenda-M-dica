package Persistencia;

public class TestaBanco {

    public static void main(String[] args) {

        BuildDeTabelas build = new BuildDeTabelas();

        build.construirTabelas();
        build.inserirDadosIniciais();
        
        System.out.println(
        build.ultimoIdentificadorDaTabela("USUARIO"));

        
    }

}
