package Entidades;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

    private int identificador;
    private String descricao;
    private String sigla;

    @Override
    public String toString() {
        return descricao;
    }
    
    

}
