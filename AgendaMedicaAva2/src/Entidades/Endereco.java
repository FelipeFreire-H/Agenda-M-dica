package Entidades;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private int identificador;
    private Cep cep;
    private String complemento;
    private int numero;
    private TipoEndereco tipoEndereco;
    private String pontoReferencia;

    @Override
    public String toString() {
        return cep.getLogradouro().getDescricao() + 
        ", " + numero;
    }
    
    

}
