package br.com.fullstack.lembretes.entidades;

import br.com.fullstack.lembretes.dto.LembreteRequisicao;
import br.com.fullstack.lembretes.enums.Prioridade;
import br.com.fullstack.lembretes.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "lembretes")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(length = 512)
    private String descricao;

    @Column(name = "lembrar_em", nullable = false)
    private LocalDateTime lembrarEm;

    @Enumerated(EnumType.STRING)
    @Column(length = 5, nullable = false)
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    @Column(length = 9, nullable = false)
    private Status status;

    public Lembrete(LembreteRequisicao req) {
        BeanUtils.copyProperties(req, this);
    }
}
