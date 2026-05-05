package dev.java._x.desafiorelogio.dto;

import java.util.List;

public record PaginaRelogioDTO(
        List<RelogioDTO> items,
        long total
) {
}
