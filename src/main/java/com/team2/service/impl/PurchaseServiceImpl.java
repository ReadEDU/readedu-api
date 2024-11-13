package com.team2.service.impl;

import com.team2.dto.purchase.PurchaseDTO;
import com.team2.dto.purchase.PurchaseResponseDTO;
import com.team2.model.entity.Donation;
import com.team2.model.entity.Purchase;
import com.team2.model.entity.User;
import com.team2.model.enums.ERole;
import com.team2.model.enums.PaymentStatus;
import com.team2.repository.DonationRepository;
import com.team2.repository.PurchaseRepository;
import com.team2.repository.UserRepository;
import com.team2.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;

    @Transactional
    @Override
    public PurchaseResponseDTO createPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = new Purchase();

        User user = userRepository.findById(purchaseDTO.getCreatorId()).orElseThrow(() -> new RuntimeException("Usario no encontrado"));
        if(user.getRole().getName() == ERole.READER){
            throw new RuntimeException("No es un creador de contenido");
        }
        purchase.setUser(user);

        if(purchaseDTO.getDonationId() != null) {
            Donation donation = donationRepository.findById(purchaseDTO.getDonationId()).orElseThrow(() -> new RuntimeException("Donation Not Found"));
            purchase.setDonation(donation);

            purchase.setTotal(donation.getAmount());
        }
        purchase.setCreated_at(LocalDateTime.now());
        purchase.setPayment_status(PaymentStatus.PENDING);

        PurchaseResponseDTO responseDTO = new PurchaseResponseDTO();
        responseDTO.setId(purchase.getId());
        responseDTO.setTotal(purchase.getTotal());
        responseDTO.setPaymentStatus(purchase.getPayment_status().name());
        responseDTO.setCreatedAt(purchase.getCreated_at().toString());
        responseDTO.setCreatorId(purchase.getUser().getId());
        responseDTO.setDonationId(purchase.getDonation() != null ? purchase.getDonation().getId() : null);
        purchaseRepository.save(purchase);
        return mapToResponseDTO(purchase);
    }

    @Override
    public List<PurchaseResponseDTO> getPurchaseHistoryByUserId(Integer userId) {
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);

        return purchases.stream().map(purchase -> {
            PurchaseResponseDTO responseDTO = new PurchaseResponseDTO();
            responseDTO.setId(purchase.getId());
            responseDTO.setTotal(purchase.getTotal());
            responseDTO.setPaymentStatus(purchase.getPayment_status().name());
            responseDTO.setCreatedAt(purchase.getCreated_at().toString());
            responseDTO.setCreatorId(purchase.getUser().getId());
            responseDTO.setDonationId(purchase.getDonation() != null ? purchase.getDonation().getId() : null);
            return responseDTO;
        }).toList();
    }

    @Override
    public PurchaseDTO confirmPurchase(Integer purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new RuntimeException("Purchase Not Found"));
        purchase.setPayment_status(PaymentStatus.PAID);

        Purchase updatedPurchase = purchaseRepository.save(purchase);

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(updatedPurchase.getId());
        purchaseDTO.setTotal(updatedPurchase.getTotal());
        purchaseDTO.setPaymentStatus(updatedPurchase.getPayment_status().name());
        purchaseDTO.setCreatedAt(updatedPurchase.getCreated_at().toString());
        purchaseDTO.setCreatorId(updatedPurchase.getUser().getId());
        purchaseDTO.setDonationId(updatedPurchase.getDonation() != null ? updatedPurchase.getDonation().getId() : null);

        if (updatedPurchase.getDonation() != null) {
            Donation donation = updatedPurchase.getDonation();
            donation.setPayment_status(PaymentStatus.PAID);
            donationRepository.save(donation);
            }

        return purchaseDTO;
         }

    private PurchaseResponseDTO mapToResponseDTO(Purchase purchase) {
        PurchaseResponseDTO responseDTO = new PurchaseResponseDTO();
        responseDTO.setId(purchase.getId());
        responseDTO.setTotal(purchase.getTotal());
        responseDTO.setPaymentStatus(purchase.getPayment_status().name());
        responseDTO.setCreatedAt(purchase.getCreated_at().toString());
        responseDTO.setCreatorId(purchase.getUser().getId());
        responseDTO.setDonationId(purchase.getDonation() != null ? purchase.getDonation().getId() : null);
        return responseDTO;
    }

    }
