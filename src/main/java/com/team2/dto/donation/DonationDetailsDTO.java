package com.team2.dto.donation;

import lombok.Data;

@Data
public class DonationDetailsDTO {
    private Integer id;

    private float amount;
    private String creatorFirstName;
    private String creatorLastName;
}
