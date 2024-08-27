package br.com.fullstack.lembretes.repositorios;

import br.com.fullstack.lembretes.entidades.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LembreteRepositorio extends JpaRepository<Lembrete, Long> {

    List<Lembrete> findByLembrarEmBetweenOrderByLembrarEm(LocalDateTime dataInicial, LocalDateTime dataFinal);

    // Atuais (do dia atual)
    @Query("SELECT l FROM Lembrete l WHERE DATE_TRUNC('day', l.lembrarEm) = CURRENT_DATE ORDER BY l.lembrarEm")
    List<Lembrete> findByLembrarEmEqualsTodayOrderByLembrarEm();

    // Antes de agora
    @Query("SELECT l FROM Lembrete l WHERE l.lembrarEm < CURRENT_TIMESTAMP ORDER BY l.lembrarEm")
    List<Lembrete> findByLembrarEmBeforeNowOrderByLembrarEm();

    // Depois de agora
    @Query("SELECT l FROM Lembrete l WHERE l.lembrarEm >= CURRENT_TIMESTAMP ORDER BY l.lembrarEm")
    List<Lembrete> findByLembrarEmAfterNowOrderByLembrarEm();

}
