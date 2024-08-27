package br.com.fullstack.lembretes.servicos;

import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.dto.LembreteResposta;

import java.time.LocalDateTime;
import java.util.List;

public interface LembreteServico {

    LembreteResposta criar(LembreteRequisicao lembrete);

    List<LembreteResposta> buscarTodos(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);

    List<LembreteResposta> buscarAtuais();

    List<LembreteResposta> buscarProximos();

    List<LembreteResposta> buscarAnteriores();

    LembreteResposta buscarPorId(Long id);

    LembreteResposta alterar(Long id, LembreteRequisicao lembrete);

    void excluir(Long id);

}
