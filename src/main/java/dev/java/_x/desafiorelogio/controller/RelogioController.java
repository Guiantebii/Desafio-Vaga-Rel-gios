package dev.java._x.desafiorelogio.controller;

import dev.java._x.desafiorelogio.dto.*;
import dev.java._x.desafiorelogio.service.RelogioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/relogios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RelogioController {
    private final RelogioService relogioService;

    @GetMapping
    public PaginaRelogioDTO listar(
            @RequestParam(defaultValue = "1") int pagina,
            @RequestParam(defaultValue = "12") int porPagina,
            @RequestParam(required = false) String busca,
            @RequestParam(required = false)  String marca,
            @RequestParam(required = false)  String tipoMovimento,
            @RequestParam(required = false)  String materialCaixa,
            @RequestParam(required = false)  String tipoVidro,
            @RequestParam(required = false)  Integer resistenciaMin,
            @RequestParam(required = false)  Integer resistenciaMax,
            @RequestParam(required = false)  Long precoMin,
            @RequestParam(required = false)  Long precoMax,
            @RequestParam(required = false)  Integer diametroMin,
            @RequestParam(required = false)  Integer diametroMax,
            @RequestParam(required = false)  String ordenar

    ){
        return relogioService.listar(pagina,
                porPagina,
                busca,
                marca,
                tipoMovimento,
                materialCaixa,
                tipoVidro,
                resistenciaMin,
                resistenciaMax,
                precoMin,
                precoMax,
                diametroMin,
                diametroMax,
                ordenar);
    }
    @GetMapping("/{id}")
    public RelogioDTO buscarPorId(@PathVariable UUID id){
        return relogioService.buscarPorId(id);
    }
    @PostMapping
    public RelogioDTO criar(@Valid @RequestBody CriarRelogioRequest req){
        return relogioService.criar(req);
    }
    @PutMapping("{id}")
    public RelogioDTO atualizar(@PathVariable UUID id, @Valid @RequestBody AtualizarRelogioRequest req){
        return relogioService.atualizar(id,req);
    }
    @DeleteMapping("{id}")
    public void remover(@PathVariable UUID id){
        relogioService.deletar(id);
    }
}
