package br.com.fullstack.lembretes.servicos;

import br.com.fullstack.lembretes.configs.excecoes.NaoEncontradoExcecao;
import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.dto.LembreteResposta;
import br.com.fullstack.lembretes.entidades.Lembrete;
import br.com.fullstack.lembretes.enums.Status;
import br.com.fullstack.lembretes.repositorios.LembreteRepositorio;
import br.com.fullstack.lembretes.utils.DataHoraUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<LembreteResposta> buscarTodos(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal) {
        log.info("Buscando todos os lembretes");

        if (dataHoraInicial == null) {
            return repositorio.findAll()
                    .stream().map(LembreteResposta::new).toList();
        }

        if (dataHoraFinal == null) {
            dataHoraFinal = DataHoraUtil.ultimaHoraDoDia(dataHoraInicial);
        }

        log.info("Filtrando pelo intervalo de datas: {} e {}", dataHoraInicial, dataHoraFinal);
        return repositorio.findByLembrarEmBetweenOrderByLembrarEm(dataHoraInicial, dataHoraFinal)
                .stream().map(LembreteResposta::new).toList();
    }

    @Override
    public Page<LembreteResposta> buscarTodosPaginado(Pageable pageable) {
        return repositorio.findAll(pageable).map(LembreteResposta::new);
    }

    @Override
    public List<LembreteResposta> buscarAtuais() {
        log.info("Buscando todos os lembretes atuais");

        return repositorio.findByLembrarEmEqualsTodayOrderByLembrarEm()
                .stream().map(LembreteResposta::new).toList();
    }

    @Override
    public List<LembreteResposta> buscarProximos() {
        log.info("Buscando todos os próximos lembretes");

        return repositorio.findByLembrarEmAfterNowOrderByLembrarEm()
                .stream().map(LembreteResposta::new).toList();
    }

    @Override
    public List<LembreteResposta> buscarAnteriores() {
        log.info("Buscando todos os lembretes anteriores");

        return repositorio.findByLembrarEmBeforeNowOrderByLembrarEm()
                .stream().map(LembreteResposta::new).toList();
    }

    @Override
    public LembreteResposta buscarPorId(Long id) {
        return new LembreteResposta(buscarEntidadePorId(id));
    }

    private Lembrete buscarEntidadePorId(Long id) {
        log.info("Buscando lembrete por ID: {}", id);
        Lembrete lembrete = repositorio.findById(id)
                .orElseThrow(() -> new NaoEncontradoExcecao("Lembrete não encontrado com ID: " + id));

        log.info("Lembrete encontrado com ID: {}", id);
        return lembrete;
    }

    @Override
    public LembreteResposta alterar(Long id, LembreteRequisicao req) {
        log.info("Alterando lembrete com ID: {}", id);

        Lembrete versaoAnterior = buscarEntidadePorId(id);

        Lembrete lembrete = new Lembrete(req);
        lembrete.setId(id);
        lembrete.setStatus(versaoAnterior.getStatus());

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

    @Override
    public void pedente(Long id) {
        mudarStatus(id, Status.PENDENTE);
    }

    @Override
    public void concluir(Long id) {
        mudarStatus(id, Status.CONCLUIDO);
    }

    private void mudarStatus(Long id, Status status) {
        log.info("Mudar status do Lembrete com ID {} para: {}", id, status);

        Lembrete lembrete = buscarEntidadePorId(id);
        lembrete.setStatus(status);
        repositorio.save(lembrete);
    }

}
