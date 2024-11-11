package com.team2.dto.purchase;

import lombok.Data;

@Data
public class PurchaseDTO {
    private Integer creatorId;
    private Integer donationId;
    private Integer id;
    private Float total;
    private String paymentStatus;
    private String createdAt;
}
