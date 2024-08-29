package br.com.fullstack.lembretes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Objeto de criação/edição de lembrete")
public class LembreteRequisicao extends LembreteAbstrato {
}
