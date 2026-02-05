package com.javanauta.agendadortarefa.infrastructure.business;


import com.javanauta.agendadortarefa.infrastructure.business.dto.TarefasDTO;
import com.javanauta.agendadortarefa.infrastructure.business.mapper.TarefaUpdateConverter;
import com.javanauta.agendadortarefa.infrastructure.business.mapper.TarefasConverter;
import com.javanauta.agendadortarefa.infrastructure.entity.TarefasEntity;
import com.javanauta.agendadortarefa.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendadortarefa.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendadortarefa.infrastructure.repository.TarefasRepository;
import com.javanauta.agendadortarefa.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    public final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO) {

        String email = jwtUtil.extraiEmailToken(token.substring(7));

        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        tarefasDTO.setEmailUsuario(email);
        TarefasEntity tarefaEntity = tarefasConverter.paraTarefasEntity(tarefasDTO);

        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(tarefaEntity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extraiEmailToken(token.substring(7));
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por ID; ID da tarefa não encontrado!! " + id + e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Id de tarefa não encontrado " + id));
            tarefasEntity.setStatusNotificacaoEnum(status);

            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o Status da tarefa!! " + e.getCause());
        }
    }

    public TarefasDTO updateTarefa(TarefasDTO tarefasDTO, String id){
        try {
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Id de tarefa não encontrado " + id));

            tarefaUpdateConverter.updateConverter(tarefasDTO, tarefasEntity);

            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(tarefasEntity));

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Id de tarefa não encontrado " + id + e.getCause());
        }
    }
}
