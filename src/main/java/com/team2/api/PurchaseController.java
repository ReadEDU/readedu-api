package com.team2.api;


import com.team2.dto.purchase.PurchaseDTO;
import com.team2.dto.purchase.PurchaseResponseDTO;
import com.team2.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        return ResponseEntity.ok(purchaseService.createPurchase(purchaseDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseResponseDTO>> getPurchaseHistoryByUserId(@PathVariable Integer userId) {
        List<PurchaseResponseDTO> purchaseHistory = purchaseService.getPurchaseHistoryByUserId(userId);
        return ResponseEntity.ok(purchaseHistory);
    }
}
