package br.com.fullstack.lembretes.servicos;

import br.com.fullstack.lembretes.configs.excecoes.NaoEncontradoExcecao;
import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.dto.LembreteResposta;
import br.com.fullstack.lembretes.entidades.Lembrete;
import br.com.fullstack.lembretes.enums.Status;
import br.com.fullstack.lembretes.repositorios.LembreteRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LembreteServicoImpl implements LembreteServico {

    private final LembreteRepositorio repositorio;

    @Override
    public LembreteResposta criar(LembreteRequisicao req) {
        log.info("Criando lembrete: {}", req.getTitulo());

        Lembrete lembrete = new Lembrete(req);
        lembrete.setStatus(Status.PENDENTE);

        log.info("Salvando lembrete: {}", req.getTitulo());
        repositorio.save(lembrete);

        log.info("Lembrete salvo: {} - {}", lembrete.getId(), lembrete.getTitulo());
        return new LembreteResposta(lembrete);
    }

    @Override
    public List<LembreteResposta> buscarTodos() {
        log.info("Buscando todos os lembretes");
        return repositorio.findAll().stream().map(LembreteResposta::new).toList();
    }

    @Override
    public LembreteResposta buscarPorId(Long id) {
        log.info("Buscando lembrete por ID: {}", id);
        LembreteResposta res = new LembreteResposta(
                repositorio.findById(id)
                        .orElseThrow(() -> new NaoEncontradoExcecao(
                                "Lembrete não encontrado com ID: " + id
                        ))
        );

        log.info("Lembrete encontrado com ID: {}", id);
        return res;
    }

    @Override
    public LembreteResposta alterar(Long id, LembreteRequisicao req) {
        log.info("Alterando lembrete com ID: {}", id);

        buscarPorId(id);

        Lembrete lembrete = new Lembrete(req);
        lembrete.setId(id);

        log.info("Salvando lembrete com ID: {}", id);
        repositorio.save(lembrete);

        log.info("Lembrete salvo: {} - {}", id, lembrete.getTitulo());
        return new LembreteResposta(lembrete);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo lembrete com ID: {}", id);

        buscarPorId(id);
        repositorio.deleteById(id);

        log.info("Lembrete com ID {} foi ELIMINADO!", id);
        log.info("Lembrete com ID {} Virou PÓ!", id);
        log.info("Lembrete com ID {} já era! ;'(", id);
    }

}
