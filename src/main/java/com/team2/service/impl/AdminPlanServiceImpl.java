package com.team2.service.impl;

import com.team2.dto.plan.PlanDTO;
import com.team2.exception.ResourceNotFoundException;
import com.team2.exception.BadRequestException;
import com.team2.mapper.PlanMapper;
import com.team2.model.entity.Plan;
import com.team2.repository.PlanRepository;
import com.team2.service.AdminPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminPlanServiceImpl implements AdminPlanService {
    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PlanDTO> findAll() {
        List<Plan> plans = planRepository.findAll();
        return plans.stream()
                .map(planMapper::toPlanDTO)
                .toList();
    }

    @Transactional
    @Override
    public PlanDTO create(PlanDTO planDTO) {
        planRepository.findByType(planDTO.getType())
                .ifPresent(existingPlan -> {
                    throw new BadRequestException("Plan already exists");
                });
        Plan plan = planMapper.toPlanEntity(planDTO);
        Plan savedPlan = planRepository.save(plan);
        return planMapper.toPlanDTO(savedPlan);
    }

    @Transactional(readOnly = true)
    @Override
    public PlanDTO findById(Integer id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EL Plan with Id " +id+ "not found"));
        return planMapper.toPlanDTO(plan);
    }

    @Transactional
    @Override
    public PlanDTO update(Integer id, PlanDTO planDTO) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EL Plan with Id " +id+ "not found"));
        planRepository.findByType(planDTO.getType())
                .ifPresent(existingPlan -> {
                    throw new BadRequestException("Ya existe un plan con el mismo nombre");
                });

        plan.setType(planDTO.getType());
        plan.setPrice(planDTO.getPrice());

        Plan updatedPlan = planRepository.save(plan);
        return planMapper.toPlanDTO(updatedPlan);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EL Plan with Id " +id+ "not found"));
        planRepository.delete(plan);
    }

}