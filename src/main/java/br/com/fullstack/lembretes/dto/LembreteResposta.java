package br.com.fullstack.lembretes.dto;

import br.com.fullstack.lembretes.entidades.Lembrete;
import br.com.fullstack.lembretes.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Objeto de resposta de lembrete")
public class LembreteResposta extends LembreteAbstrato {

    @Schema(description = "Identificador do lembrete", example = "1")
    private Long id;

    @Schema(description = "Status do lembrete", example = "PENDENTE")
    private Status status;

    public LembreteResposta(Lembrete lembrete) {
        BeanUtils.copyProperties(lembrete, this);
    }
}
