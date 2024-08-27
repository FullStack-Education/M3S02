package br.com.fullstack.lembretes.utils;

import java.time.LocalDateTime;

public class DataHoraUtil {
    private DataHoraUtil() {
    }

    public static LocalDateTime primeiraHoraDoDia(LocalDateTime data) {
        return data.withHour(0).withMinute(0).withSecond(0);
    }

    public static LocalDateTime ultimaHoraDoDia(LocalDateTime data) {
        return data.withHour(23).withMinute(59).withSecond(59);
    }
}
