package com.team2.dto.plan;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlanDTO {

    private Integer id;

    @NotBlank(message = "El tipo de plan es obligatorio")
    @Size(max = 50, message = "El tipo de plan debe tener 50 caracteres o menos")
    private String type;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private Float price;
}