package br.com.fullstack.lembretes.configs;

import br.com.fullstack.lembretes.configs.excecoes.NaoEncontradoExcecao;
import br.com.fullstack.lembretes.dto.ErroDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExcecaoManipulador {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handler(Exception e) {
        log.error("Falha catastrófica: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErroDto.builder()
                                .erro(Exception.class.getSimpleName())
                                .codigo("500")
                                .mensagem(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(NaoEncontradoExcecao.class)
    public ResponseEntity handler(NaoEncontradoExcecao e) {
        log.error("Ocorreu um ERRO: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErroDto.builder()
                        .erro(NaoEncontradoExcecao.class.getSimpleName())
                        .codigo("404")
                        .mensagem(e.getMessage())
                        .build()
        );
    }

}
