package com.team2.service.impl;

import com.team2.dto.donation.DonationCreateDTO;
import com.team2.dto.donation.DonationDetailsDTO;
import com.team2.exception.InvalidOperationException;
import com.team2.exception.ResourceNotFoundException;
import com.team2.mapper.DonationMapper;
import com.team2.model.entity.Donation;
import com.team2.model.entity.User;
import com.team2.repository.DonationRepository;
import com.team2.repository.UserRepository;
import com.team2.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final DonationMapper donationMapper;

    @Transactional
    @Override
    public DonationDetailsDTO createDonation(DonationCreateDTO donationCreateDTO){
        User user = userRepository.findById(donationCreateDTO.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Creador no encontrado con id: " + donationCreateDTO.getCreatorId()));

        if (user.getCreator() == null) {
            throw new InvalidOperationException("Solo los creadores pueden recibir donaciones");
        }

        Donation donation = donationMapper.toEntity(donationCreateDTO);
        donation.setAmount(donationCreateDTO.getAmount());
        donation.setCreated_at(LocalDateTime.now());
        donation.setUser(user);

        return donationMapper.toDetailsDTO(donationRepository.save(donation));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DonationDetailsDTO> getDonorsAndAmounts(Integer creatorId) {
        List<Donation> donations = donationRepository.findDonationsByUserId(creatorId);


        User user = userRepository.findById(creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Creador no encontrado con id: " + creatorId));

        if (user.getCreator() == null) {
            throw new InvalidOperationException("Solo los creadores pueden visualizar su lista de donaciones");
        }

        if (donations.isEmpty()) {
            throw new ResourceNotFoundException("No existen donaciones para el creador ID: " + creatorId);
        }

        return donations.stream().map(donationMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Float getTotalDonationsByUserId(Integer creatorId) {
        User user = userRepository.findById(creatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Creador no encontrado con id: " + creatorId));
        if (user.getCreator() == null) {
            throw new InvalidOperationException("Solo los creadores pueden visualizar su total de donaciones");
        }
        return donationRepository.getTotalDonationsByUserId(creatorId);
    }
}
