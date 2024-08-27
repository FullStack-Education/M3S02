package br.com.fullstack.lembretes.servicos;

import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.dto.LembreteResposta;

import java.util.List;

public interface LembreteServico {

    LembreteResposta criar(LembreteRequisicao lembrete);

    List<LembreteResposta> buscarTodos();

    LembreteResposta buscarPorId(Long id);

    LembreteResposta alterar(Long id, LembreteRequisicao lembrete);

    void excluir(Long id);

}
