package br.com.fullstack.lembretes.controladores;

import br.com.fullstack.lembretes.dto.LembreteResposta;
import br.com.fullstack.lembretes.servicos.LembreteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("lembretes")
@Tag(name = "Lembrete - Consultas", description = "Consultas gerais de lembretes")
public class LembreteConsultaControlador {

    private final LembreteServico servico;

    @Operation(summary = "Buscar lembretes (resposta paginada)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembretes encontrados",
                    useReturnTypeSchema = true
            ),
    })
    @GetMapping("paginado")
    public Page<LembreteResposta> getPaginado(
            @ParameterObject
            @PageableDefault(sort = {"lembrarEm,ASC"}) Pageable pageable
    ) {
        log.info("GET /lembretes/paginado -> Begin");
        Page<LembreteResposta> res = servico.buscarTodosPaginado(pageable);

        log.info("GET /lembretes/paginado -> End");
        return res;
    }

    @Operation(summary = "Buscar lembretes (padrão HATEOAS)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembretes encontrados",
                    useReturnTypeSchema = true
            ),
    })
    @GetMapping("paginado/hateoas")
    public PagedModel getPaginadoHateoas(
            @ParameterObject
            @PageableDefault(sort = {"lembrarEm,ASC"}) Pageable pageable,
            @Parameter(hidden = true) PagedResourcesAssembler assembler
    ) {
        log.info("GET /lembretes/paginado/hateoas -> Begin");
        Page<LembreteResposta> res = servico.buscarTodosPaginado(pageable);

        log.info("GET /lembretes/paginado/hateoas -> End");
        return assembler.toModel(res);
    }

    @Operation(summary = "Buscar lembretes atuais")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembretes encontrados",
                    useReturnTypeSchema = true
            ),
    })
    @GetMapping("atuais")
    public List<LembreteResposta> getAtuais() {
        log.info("GET /lembretes/atuais -> Begin");
        List<LembreteResposta> res = servico.buscarAtuais();

        log.info("GET /lembretes/atuais -> End");
        return res;
    }

    @Operation(summary = "Buscar próximos lembretes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembretes encontrados",
                    useReturnTypeSchema = true
            ),
    })
    @GetMapping("proximos")
    public List<LembreteResposta> getProximos() {
        log.info("GET /lembretes/proximos -> Begin");
        List<LembreteResposta> res = servico.buscarProximos();

        log.info("GET /lembretes/proximos -> End");
        return res;
    }

    @Operation(summary = "Buscar lembretes anteriores")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lembretes encontrados",
                    useReturnTypeSchema = true
            ),
    })
    @GetMapping("anteriores")
    public List<LembreteResposta> getAnteriores() {
        log.info("GET /lembretes/anteriores -> Begin");
        List<LembreteResposta> res = servico.buscarAnteriores();

        log.info("GET /lembretes/anteriores -> End");
        return res;
    }

}
