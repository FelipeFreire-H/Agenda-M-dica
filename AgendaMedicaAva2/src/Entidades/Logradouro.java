
package Entidades;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Logradouro {
    
    private int identificador;
    private String descricao;
    private Bairro bairro;
    
}
