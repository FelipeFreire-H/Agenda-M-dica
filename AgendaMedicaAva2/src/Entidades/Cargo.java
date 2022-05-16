package Entidades;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

    private int identificador;
    private String descricao;
    private boolean ativo;

    @Override
    public String toString() {
        return this.descricao;
    }

}
