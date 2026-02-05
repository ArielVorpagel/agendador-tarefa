package com.javanauta.agendadortarefa.infrastructure.controller;


import com.javanauta.agendadortarefa.infrastructure.business.TarefasService;
import com.javanauta.agendadortarefa.infrastructure.business.dto.TarefasDTO;
import com.javanauta.agendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravaTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                  @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaTarefasAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal
    ){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }
    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader("Authorization") String token){
        tarefasService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }
    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                   @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.updateTarefa(tarefasDTO, id));
    }

}
