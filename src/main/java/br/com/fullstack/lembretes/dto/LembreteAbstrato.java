package br.com.fullstack.lembretes.dto;

import br.com.fullstack.lembretes.enums.Prioridade;
import br.com.fullstack.lembretes.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class LembreteAbstrato {

    private String titulo;
    private String descricao;
    private LocalDateTime lembrarEm;
    private Prioridade prioridade;
    private Status status;

}
