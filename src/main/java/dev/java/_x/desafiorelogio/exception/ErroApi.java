package dev.java._x.desafiorelogio.exception;

import java.time.Instant;
import java.util.List;

public record ErroApi(
        Instant timestamp,
        int status,
        String erro,
        String mensagem,
        String caminho,
        List<ErroCampo> errosCampo
) {
    public record ErroCampo(String campo,String mensagem){}
}
