package br.com.fullstack.lembretes.repositorios;

import br.com.fullstack.lembretes.entidades.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepositorio extends JpaRepository<Lembrete, Long> {
}
