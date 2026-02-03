package com.javanauta.agendadortarefa.infrastructure.business.mapper;


import com.javanauta.agendadortarefa.infrastructure.business.dto.TarefasDTO;
import com.javanauta.agendadortarefa.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasDTO paraTarefasDTO(TarefasEntity tarefaEntity);

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);
}
