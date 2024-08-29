package br.com.fullstack.lembretes.controladores;

import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.dto.LembreteResposta;
import br.com.fullstack.lembretes.servicos.LembreteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("lembretes")
@Tag(name = "Lembrete - CRUD", description = "CRUD de lembretes")
public class LembreteCrudControlador {

    private final LembreteServico servico;

    @Operation(summary = "Criar novo lembrete")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Lembrete criado",
                    useReturnTypeSchema = true
            ),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LembreteResposta post(@Valid @RequestBody LembreteRequisicao req) {
        log.info("POST /lembretes -> Begin");
        LembreteResposta res = servico.criar(req);

        log.info("POST /lembretes -> End");
        return res;
    }

    @Operation(summary = "Buscar lembretes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembretes encontrados",
                    useReturnTypeSchema = true
            ),
    })
    @GetMapping
    public List<LembreteResposta> get(
            @Parameter(description = "Data e hora inicial", example = "1999-01-01T12:00")
            @RequestParam(required = false) LocalDateTime dataHoraInicial,
            @Parameter(description = "Data e hora final", example = "2030-12-31T18:00")
            @RequestParam(required = false) LocalDateTime dataHoraFinal
    ) {
        log.info("GET /lembretes -> Begin");
        List<LembreteResposta> res = servico.buscarTodos(dataHoraInicial, dataHoraFinal);

        log.info("GET /lembretes -> End");
        return res;
    }

    @Operation(summary = "Buscar lembrete por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembrete encontrado",
                    useReturnTypeSchema = true
            ),
    })
    @GetMapping("{id}")
    public LembreteResposta getPorId(
            @Parameter(description = "ID do lembrete", example = "1")
            @PathVariable Long id
    ) {
        log.info("GET /lembretes/{} -> Begin", id);
        LembreteResposta res = servico.buscarPorId(id);

        log.info("GET /lembretes/{} -> End", id);
        return res;
    }

    @Operation(summary = "Editar lembrete por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembrete alterado",
                    useReturnTypeSchema = true
            ),
    })
    @PutMapping("{id}")
    public LembreteResposta put(
            @Parameter(description = "ID do lembrete", example = "1")
            @PathVariable Long id,
            @RequestBody LembreteRequisicao req
    ) {
        log.info("PUT /lembretes/{} -> Begin", id);
        LembreteResposta res = servico.alterar(id, req);

        log.info("PUT /lembretes/{} -> End", id);
        return res;
    }

    @Operation(summary = "Excluir lembrete por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Lembrete excluído"
            ),
    })
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "ID do lembrete", example = "1")
            @PathVariable Long id
    ) {
        log.info("DELETE /lembretes/{} -> Begin", id);
        servico.excluir(id);
        log.info("DELETE /lembretes/{} -> End", id);
    }

}
