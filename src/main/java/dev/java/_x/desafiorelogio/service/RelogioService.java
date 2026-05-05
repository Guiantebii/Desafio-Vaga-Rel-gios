package dev.java._x.desafiorelogio.service;

import dev.java._x.desafiorelogio.dto.AtualizarRelogioRequest;
import dev.java._x.desafiorelogio.dto.CriarRelogioRequest;
import dev.java._x.desafiorelogio.dto.PaginaRelogioDTO;
import dev.java._x.desafiorelogio.dto.RelogioDTO;
import dev.java._x.desafiorelogio.entity.Relogio;
import dev.java._x.desafiorelogio.entity.enums.MaterialCaixa;
import dev.java._x.desafiorelogio.entity.enums.TipoMovimento;
import dev.java._x.desafiorelogio.entity.enums.TipoVidro;
import dev.java._x.desafiorelogio.exception.NaoEncontradoException;
import dev.java._x.desafiorelogio.mapper.RelogioMapper;
import dev.java._x.desafiorelogio.repository.RelogioRepository;
import dev.java._x.desafiorelogio.service.enums.OrdenacaoRelogios;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

import static dev.java._x.desafiorelogio.service.RelogioSpecs.*;

@Service
@RequiredArgsConstructor
public class RelogioService {
    private final RelogioRepository relogioRepository;
    private final RelogioMapper relogioMapper;

    public PaginaRelogioDTO listar(
            int pagina,
            int porPagina,
            String busca,
            String marca,
            String tipoMovimento,
            String materialCaixa,
            String tipoVidro,
            Integer resistenciaMin,
            Integer resistenciaMax,
            Long precoMin,
            Long precoMax,
            Integer diametroMin,
            Integer diametroMax,
            String ordenar

    ){
        int paginaSegura = Math.max(1,pagina);
        int porPaginaSeguro = Math.min(60, Math.max(1,porPagina));

        TipoMovimento movimento = TipoMovimento.fromApi(tipoMovimento);
        MaterialCaixa material = MaterialCaixa.fromApi(materialCaixa);
        TipoVidro vidro = TipoVidro.fromApi(tipoVidro);

        OrdenacaoRelogios ordenacao = OrdenacaoRelogios.fromApi(ordenar);

        Sort sort = switch (ordenacao){
            case MAIS_RECENTES -> Sort.by(Sort.Direction.DESC,"criadoEm");
            case PRECO_CRESC -> Sort.by(Sort.Direction.ASC,"precoEmCentavos");
            case PRECO_DESC -> Sort.by(Sort.Direction.DESC,"precoEmCentavos");
            case DIAMETRO_CRESC -> Sort.by(Sort.Direction.ASC,"diametroMm");
            case RESISTENCIA_DESC -> Sort.by(Sort.Direction.DESC,"resistenciaAguaM");
        };

        Pageable pageable = PageRequest.of(paginaSegura - 1,porPaginaSeguro,sort);

        Specification<Relogio> spec = Specification.where(busca(busca))
                .and(marcaIgual(marca))
                .and(tipoMovimentoIgual(movimento))
                .and(materialCaixaIgual(material))
                .and(tipoVidroIgual(vidro))
                .and(resistenciaAguaEntre(resistenciaMin,resistenciaMax))
                .and(precoEntre(precoMin,precoMax))
                .and(diametroEntre(diametroMin,diametroMax));
        Page<Relogio> resultado = relogioRepository.findAll(spec,pageable);
        return new PaginaRelogioDTO(
                resultado.getContent().stream().map(relogioMapper::toDTO).toList(),
                resultado.getTotalElements()
        );
    }

    public RelogioDTO buscarPorId(UUID id){
        Relogio r = relogioRepository.findById(id).orElseThrow(()-> new NaoEncontradoException("Relógio não encontrado" + id));
        return relogioMapper.toDTO(r);
    }
    public RelogioDTO criar(CriarRelogioRequest req){
        Relogio r = Relogio.builder()
                .id(UUID.randomUUID())
                .marca(req.marca())
                .modelo(req.modelo())
                .referencia(req.referencia())
                .tipoMovimento(TipoMovimento.fromApi(req.tipoMovimento()))
                .materialCaixa(MaterialCaixa.fromApi(req.materialCaixa()))
                .tipoVidro(TipoVidro.fromApi(req.tipoVidro()))
                .resistenciaAguaM(req.resistenciaAguaM())
                .diametroMm(req.diametroMm())
                .lugToLugMm(req.larguraLugMm())
                .espessuraMm(req.espessuraMm())
                .larguraLugMm(req.larguraLugMm())
                .precoEmCentavos(req.precoEmCentavos())
                .urlImagem(req.urlImagem())
                .criadoEm(Instant.now())
                .build();
        return relogioMapper.toDTO(relogioRepository.save(r));
    }

    public RelogioDTO atualizar(UUID id, AtualizarRelogioRequest req){
        Relogio r = relogioRepository.findById(id).orElseThrow(()-> new NaoEncontradoException("Relógio não encontrado" + id));
            r.setMarca(req.marca());
            r.setModelo(req.modelo());
            r.setReferencia(req.referencia());
            r.setTipoMovimento(TipoMovimento.fromApi(req.tipoMovimento()));
            r.setMaterialCaixa(MaterialCaixa.fromApi(req.materialCaixa()));
            r.setTipoVidro(TipoVidro.fromApi(req.tipoVidro()));
            r.setDiametroMm(req.diametroMm());
            r.setEspessuraMm(req.espessuraMm());
            r.setLugToLugMm(req.lugToLugMm());
            r.setLarguraLugMm(req.larguraLugMm());
            r.setResistenciaAguaM(req.resistenciaAguaM());
            r.setPrecoEmCentavos(req.precoEmCentavos());
        return relogioMapper.toDTO(relogioRepository.save(r));
    }
    public void deletar(UUID id){
        if(!relogioRepository.existsById(id)) {
            throw new NaoEncontradoException("Relogio não encontrado" + id);
        }
        relogioRepository.deleteById(id);
    }
}
