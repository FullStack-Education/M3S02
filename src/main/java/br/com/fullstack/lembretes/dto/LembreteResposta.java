package br.com.fullstack.lembretes.dto;

import br.com.fullstack.lembretes.entidades.Lembrete;
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

    public LembreteResposta(Lembrete lembrete) {
        BeanUtils.copyProperties(lembrete, this);
    }
}
