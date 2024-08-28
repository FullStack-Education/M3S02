package br.com.fullstack.lembretes.controladores;

import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.dto.LembreteResposta;
import br.com.fullstack.lembretes.servicos.LembreteServico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("lembretes")
public class LembreteConsultaControlador {

    private final LembreteServico servico;

    @GetMapping("paginado")
    public Page<LembreteResposta> getPaginado(@PageableDefault Pageable pageable) {
        log.info("GET /lembretes/paginado -> Begin");
        Page<LembreteResposta> res = servico.buscarTodosPaginado(pageable);

        log.info("GET /lembretes/paginado -> End");
        return res;
    }

    @GetMapping("paginado/hateoas")
    public PagedModel getPaginadoHateoas(
            @PageableDefault Pageable pageable,
            PagedResourcesAssembler assembler
    ) {
        log.info("GET /lembretes/paginado/hateoas -> Begin");
        Page<LembreteResposta> res = servico.buscarTodosPaginado(pageable);

        log.info("GET /lembretes/paginado/hateoas -> End");
        return assembler.toModel(res);
    }

    @GetMapping("atuais")
    public List<LembreteResposta> getAtuais() {
        log.info("GET /lembretes/atuais -> Begin");
        List<LembreteResposta> res = servico.buscarAtuais();

        log.info("GET /lembretes/atuais -> End");
        return res;
    }

    @GetMapping("proximos")
    public List<LembreteResposta> getProximos() {
        log.info("GET /lembretes/proximos -> Begin");
        List<LembreteResposta> res = servico.buscarProximos();

        log.info("GET /lembretes/proximos -> End");
        return res;
    }

    @GetMapping("anteriores")
    public List<LembreteResposta> getAnteriores() {
        log.info("GET /lembretes/anteriores -> Begin");
        List<LembreteResposta> res = servico.buscarAnteriores();

        log.info("GET /lembretes/anteriores -> End");
        return res;
    }

}
