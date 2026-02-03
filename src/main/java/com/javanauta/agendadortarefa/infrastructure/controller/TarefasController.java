package com.javanauta.agendadortarefa.infrastructure.controller;


import com.javanauta.agendadortarefa.infrastructure.business.TarefasService;
import com.javanauta.agendadortarefa.infrastructure.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
