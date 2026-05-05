package dev.java._x.desafiorelogio.config;

import dev.java._x.desafiorelogio.entity.Relogio;
import dev.java._x.desafiorelogio.entity.enums.MaterialCaixa;
import dev.java._x.desafiorelogio.entity.enums.TipoMovimento;
import dev.java._x.desafiorelogio.entity.enums.TipoVidro;
import dev.java._x.desafiorelogio.repository.RelogioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class CarregadorDadosInicial {

    private final RelogioRepository relogioRepository;

    @Bean
    CommandLineRunner seedRelogios(){
        return args -> {
            if (relogioRepository.count() > 0 )return;

            Instant agora = Instant.now();

            List<Relogio> relogios = List.of(
                    Relogio.builder()
                            .id(UUID.randomUUID())
                            .marca("Cssio")
                            .modelo("F-91WD")
                            .referencia("1234")
                            .tipoMovimento(TipoMovimento.QUARTZ)
                            .materialCaixa(MaterialCaixa.RESINA)
                            .tipoVidro(TipoVidro.ACRILICO)
                            .resistenciaAguaM(30)
                            .diametroMm(35)
                            .lugToLugMm(38)
                            .espessuraMm(9)
                            .larguraLugMm(18)
                            .precoEmCentavos(12990)
                            .urlImagem("123")
                            .criadoEm(agora.minusSeconds(5000))
                            .build(),
                    Relogio.builder()
                            .id(UUID.randomUUID())
                            .marca("Seiko")
                            .modelo("Diver 200m")
                            .referencia("890")
                            .tipoMovimento(TipoMovimento.AUTOMATICO)
                            .materialCaixa(MaterialCaixa.ACO)
                            .tipoVidro(TipoVidro.MINERAL)
                            .resistenciaAguaM(42)
                            .diametroMm(45)
                            .lugToLugMm(48)
                            .espessuraMm(12)
                            .larguraLugMm(22)
                            .precoEmCentavos(15990)
                            .urlImagem("456")
                            .criadoEm(agora.minusSeconds(3000))
                            .build(),
                    Relogio.builder()
                            .id(UUID.randomUUID())
                            .marca("Citizen")
                            .modelo("Eco-Driver-Field")
                            .referencia("464")
                            .tipoMovimento(TipoMovimento.QUARTZ)
                            .materialCaixa(MaterialCaixa.ACO)
                            .tipoVidro(TipoVidro.MINERAL)
                            .resistenciaAguaM(42)
                            .diametroMm(45)
                            .lugToLugMm(48)
                            .espessuraMm(12)
                            .larguraLugMm(22)
                            .precoEmCentavos(15990)
                            .urlImagem("432")
                            .criadoEm(agora.minusSeconds(3000))
                            .build()
            );
            relogioRepository.saveAll(relogios);
        };
    }
}
