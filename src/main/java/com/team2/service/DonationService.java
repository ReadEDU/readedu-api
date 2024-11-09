package com.team2.service;


import com.team2.dto.donation.DonationCreateDTO;
import com.team2.dto.donation.DonationDetailsDTO;

import java.util.List;

public interface DonationService {
    //Metodo para crear una nueva donación en el sistema
    DonationDetailsDTO createDonation(DonationCreateDTO donationCreateDTO);
    //Obtiene una lista de donaciones hechas por un creador especifico
    List<DonationDetailsDTO> getDonorsAndAmounts(Integer creatorId);
    //Retorna el total de todas las donaciones hechas por un usuario específico
    Float getTotalDonationsByUserId(Integer creatorId);
}
