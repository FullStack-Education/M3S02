package br.com.fullstack.lembretes.controladores;

import br.com.fullstack.lembretes.servicos.LembreteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("lembretes/{id}")
@Tag(name = "Lembrete - Status", description = "Alteração de status de lembrete")
public class LembreteStatusControlador {

    private final LembreteServico servico;

    @Operation(summary = "Marcar lembrete como concluído")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lembrete concluído"),
    })
    @PatchMapping("concluir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchConcluir(
            @Parameter(description = "ID do lembrete", example = "1")
            @PathVariable Long id
    ) {
        log.info("PATCH /lembretes/{}/concluir -> Begin", id);
        servico.concluir(id);
        log.info("PATCH /lembretes/{}/concluir -> End", id);
    }

    @Operation(summary = "Marcar lembrete como pendente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lembrete pendente"),
    })
    @PatchMapping("pendente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchPendente(
            @Parameter(description = "ID do lembrete", example = "1")
            @PathVariable Long id
    ) {
        log.info("PATCH /lembretes/{}/pendente -> Begin", id);
        servico.pedente(id);
        log.info("PATCH /lembretes/{}/pendente -> End", id);
    }

}
