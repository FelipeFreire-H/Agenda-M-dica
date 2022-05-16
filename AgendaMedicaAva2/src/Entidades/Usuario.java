package Entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Pessoa {

    private String userName;
    private String senha;
    private boolean ativo;
    private Perfil perfil;
    private Date dataCadastro;

    @Override
    public String toString() {
        return getNome();
    }

}
