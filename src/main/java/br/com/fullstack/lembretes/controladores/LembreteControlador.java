package br.com.fullstack.lembretes.controladores;

import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.dto.LembreteResposta;
import br.com.fullstack.lembretes.servicos.LembreteServico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("lembretes")
public class LembreteControlador {

    private final LembreteServico servico;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LembreteResposta post(@RequestBody LembreteRequisicao req) {
        log.info("POST /lembretes -> Begin");
        LembreteResposta res = servico.criar(req);

        log.info("POST /lembretes -> End");
        return res;
    }

    @GetMapping
    public List<LembreteResposta> get() {
        log.info("GET /lembretes -> Begin");
        List<LembreteResposta> res = servico.buscarTodos();

        log.info("GET /lembretes -> End");
        return res;
    }

    @GetMapping("{id}")
    public LembreteResposta getPorId(@PathVariable Long id) {
        log.info("GET /lembretes/{} -> Begin", id);
        LembreteResposta res = servico.buscarPorId(id);

        log.info("GET /lembretes/{} -> End", id);
        return res;
    }

    @PutMapping("{id}")
    public LembreteResposta put(@PathVariable Long id, @RequestBody LembreteRequisicao req) {
        log.info("PUT /lembretes/{} -> Begin", id);
        LembreteResposta res = servico.alterar(id, req);

        log.info("PUT /lembretes/{} -> End", id);
        return res;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("DELETE /lembretes/{} -> Begin", id);
        servico.excluir(id);
        log.info("DELETE /lembretes/{} -> End", id);
    }

}
