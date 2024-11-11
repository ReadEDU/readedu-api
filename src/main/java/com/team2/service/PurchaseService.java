package com.team2.service;

import com.team2.dto.purchase.PurchaseDTO;
import com.team2.dto.purchase.PurchaseResponseDTO;
import com.team2.model.entity.Purchase;
import java.util.List;

public interface PurchaseService {
    PurchaseResponseDTO createPurchase(PurchaseDTO purchaseDTO);
    List<PurchaseResponseDTO> getPurchaseHistoryByUserId(Integer userId);
    PurchaseDTO confirmPurchase(Integer purchaseId);
}
