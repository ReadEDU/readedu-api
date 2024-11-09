package com.team2.mapper;

import com.team2.dto.donation.DonationCreateDTO;
import com.team2.dto.donation.DonationDetailsDTO;
import com.team2.model.entity.Donation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper {
    private final ModelMapper modelMapper;

    public DonationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public DonationDetailsDTO toDetailsDTO(Donation donation) {
        DonationDetailsDTO donationDetailsDTO = modelMapper.map(donation, DonationDetailsDTO.class);

        //Asignar el nombre y apellido del creador
        donationDetailsDTO.setCreatorFirstName(donation.getUser().getCreator().getFirstName());
        donationDetailsDTO.setCreatorLastName(donation.getUser().getCreator().getLastName());

        return donationDetailsDTO;
    }

    public Donation toEntity(DonationCreateDTO donationCreateDTO) {
        return modelMapper.map(donationCreateDTO, Donation.class);
    }

    public DonationCreateDTO toEntity(Donation donation) {
        return modelMapper.map(donation, DonationCreateDTO.class);

    }
}
