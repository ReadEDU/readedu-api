package com.team2.mapper;

import com.team2.dto.plan.PlanDTO;
import com.team2.model.entity.Plan;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    private final ModelMapper modelMapper;
    public PlanMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PlanDTO toPlanDTO(Plan plan) {
        PlanDTO planDTO = modelMapper.map(plan, PlanDTO.class);
        return planDTO;
    }

    public Plan toPlanEntity(PlanDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Plan.class);
    }
}