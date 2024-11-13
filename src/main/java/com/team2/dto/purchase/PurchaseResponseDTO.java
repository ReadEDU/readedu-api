package com.team2.dto.purchase;

import lombok.Data;

@Data
public class PurchaseResponseDTO {
    private Integer id;
    private Float total;
    private String paymentStatus;
    private String createdAt;
    private Integer creatorId;
    private Integer donationId;
}