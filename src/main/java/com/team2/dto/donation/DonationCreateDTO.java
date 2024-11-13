package com.team2.dto.donation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DonationCreateDTO {
    private Integer id;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto debe al menos ser 1")
    private float amount;

    @NotNull(message = "El ID del creador es obligatorio")
    private Integer creatorId;
}