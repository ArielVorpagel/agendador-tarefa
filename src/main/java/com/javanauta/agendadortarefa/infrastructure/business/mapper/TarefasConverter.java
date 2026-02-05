package com.javanauta.agendadortarefa.infrastructure.business.mapper;


import com.javanauta.agendadortarefa.infrastructure.business.dto.TarefasDTO;
import com.javanauta.agendadortarefa.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasDTO paraTarefasDTO(TarefasEntity tarefaEntity);

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntities);
}
