package com.team2.service;

import com.team2.dto.plan.PlanDTO;
import java.util.List;

public interface AdminPlanService {
    List<PlanDTO> findAll();
    PlanDTO create(PlanDTO planDTO);
    PlanDTO findById(Integer id);
    PlanDTO update(Integer id, PlanDTO planDTO);
    void delete(Integer id);
}