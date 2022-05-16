package Entidades;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    private Integer matricula;
    private Cargo cargo;
    private String descricao;
    private Date dataAdmissao;
    private Usuario usuario;
    private Date dataDesligamento;

    @Override
    public String toString() {
        return usuario.getNome();
    }

}
