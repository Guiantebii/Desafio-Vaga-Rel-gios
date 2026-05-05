package dev.java._x.desafiorelogio.dto;

import java.util.List;

public record CriarRelogioDTO(
        List<RelogioDTO> items,
        long total
) {
}
