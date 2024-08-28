package br.com.fullstack.lembretes.controladores;

import br.com.fullstack.lembretes.dto.LembreteResposta;
import br.com.fullstack.lembretes.servicos.LembreteServico;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("lembretes/{id}")
public class LembreteStatusControlador {

    private final LembreteServico servico;

    @PatchMapping("concluir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchConcluir(@PathVariable Long id) {
        log.info("PATCH /lembretes/{}/concluir -> Begin", id);
        servico.concluir(id);
        log.info("PATCH /lembretes/{}/concluir -> End", id);
    }

    @PatchMapping("pendente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchPendente(@PathVariable Long id) {
        log.info("PATCH /lembretes/{}/pendente -> Begin", id);
        servico.pedente(id);
        log.info("PATCH /lembretes/{}/pendente -> End", id);
    }

}
