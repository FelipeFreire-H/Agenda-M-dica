package Entidades;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    private int identificador;
    private String descricao;

    @Override
    public String toString() {
        return this.descricao;
    }

}
