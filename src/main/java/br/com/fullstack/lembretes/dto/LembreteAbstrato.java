package br.com.fullstack.lembretes.dto;

import br.com.fullstack.lembretes.enums.Prioridade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class LembreteAbstrato {

    @NotBlank
    @Size(min = 1, max = 255)
    @Schema(description = "Título do lembrete", example = "Ligue os faróis")
    private String titulo;

    @Size(max = 512)
    @Schema(description = "Descrição do lembrete", example = "Ao entrar na via, ligue os faróis")
    private String descricao;

    @NotNull
    @Schema(description = "Quando deve ser lembrado", example = "2024-09-01T16:45:00.000Z")
    private LocalDateTime lembrarEm;

    @NotNull
    @Schema(description = "Prioridade do lembrete", example = "MEDIA")
    private Prioridade prioridade;

}
