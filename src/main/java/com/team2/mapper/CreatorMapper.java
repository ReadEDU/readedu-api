package com.team2.mapper;

import com.team2.dto.creator.CreatorDTO;
import com.team2.model.entity.Creator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {

    private final ModelMapper modelMapper;

    public CreatorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CreatorDTO toDTO(Creator creator){
        return modelMapper.map(creator, CreatorDTO.class);
    }

    public Creator toEntity(CreatorDTO creatorDTO){
        return modelMapper.map(creatorDTO, Creator.class);
    }
}
