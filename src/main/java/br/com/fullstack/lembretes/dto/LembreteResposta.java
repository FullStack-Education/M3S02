package br.com.fullstack.lembretes.dto;

import br.com.fullstack.lembretes.entidades.Lembrete;
import br.com.fullstack.lembretes.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LembreteResposta extends LembreteAbstrato {
    private Long id;
    private Status status;

    public LembreteResposta(Lembrete lembrete) {
        BeanUtils.copyProperties(lembrete, this);
    }
}
