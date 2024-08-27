package br.com.fullstack.lembretes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroDto {
    private String erro;
    private String codigo;
    private String mensagem;
}
