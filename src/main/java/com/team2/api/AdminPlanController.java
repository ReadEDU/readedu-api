package com.team2.api;

import com.team2.dto.plan.PlanDTO;
import com.team2.service.AdminPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/plans")
@PreAuthorize("hasRole('ADMIN')")
public class AdminPlanController {
    private final AdminPlanService planService;

    @GetMapping
    public ResponseEntity<List<PlanDTO>>listPlan(){
        List<PlanDTO>plans = planService.findAll();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> getById(@PathVariable Integer id){
        PlanDTO plan = planService.findById(id);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanDTO> create(@Valid @RequestBody PlanDTO planDTO){
        PlanDTO create = planService.create(planDTO);
        return new ResponseEntity<>(create,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanDTO> update(@PathVariable Integer id, @Valid @RequestBody PlanDTO planDTO){
        PlanDTO updatePlan = planService.update(id,planDTO);
        return new ResponseEntity<>(updatePlan,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        planService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}