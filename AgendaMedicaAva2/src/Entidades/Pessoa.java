package Entidades;

import java.util.Date;
import java.util.List;
import lombok.*;

@Getter
@Setter
public abstract class Pessoa {

    private int identificador;
    private String nome;
    private String cpfCnpj;
    private char sexo;
    private Date dataNascimento;
    private List<Telefone> listaTelefone;
    private List<Endereco> listaEndereco;

}
