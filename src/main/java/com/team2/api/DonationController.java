package com.team2.api;

import com.team2.dto.donation.DonationCreateDTO;
import com.team2.dto.donation.DonationDetailsDTO;
import com.team2.dto.donation.DonationTotalDTO;
import com.team2.service.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/donation")
@PreAuthorize("hasAnyRole('ADMIN', 'CREATOR')")

public class DonationController {
    private final DonationService donationService;

    @PostMapping
    @PreAuthorize("hasRole('READER')")
    public ResponseEntity<DonationDetailsDTO> createDonation(@Valid @RequestBody DonationCreateDTO donationCreateDTO) {
        DonationDetailsDTO donationDetailsDTO = donationService.createDonation(donationCreateDTO);
        return new ResponseEntity<>(donationDetailsDTO, HttpStatus.CREATED);
    }

    @GetMapping("/donors/{creatorId}")
    public ResponseEntity<List<DonationDetailsDTO>> getDonorsAndAmounts(@PathVariable Integer creatorId) {
        List<DonationDetailsDTO> donations = donationService.getDonorsAndAmounts(creatorId);
        if (donations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/total/{creatorId}")
    public ResponseEntity<DonationTotalDTO> getDonationAmount(@PathVariable Integer creatorId) {
        Float totalDonations = donationService.getTotalDonationsByUserId(creatorId);
        if (totalDonations == null) {
            return ResponseEntity.notFound().build();
        }
        DonationTotalDTO donationTotalDTO = new DonationTotalDTO(totalDonations);
        return ResponseEntity.ok(donationTotalDTO);
    }
}
