package Entidades;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bairro {

    private int identificador;
    private String descricao;
    private Cidade cidade;

}
