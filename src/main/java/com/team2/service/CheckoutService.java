package com.team2.service;

import com.team2.dto.payments.PaymentCaptureResponse;
import com.team2.dto.payments.PaymentOrderResponse;

public interface CheckoutService {
    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl);
    PaymentCaptureResponse capturePayment(String orderId);
}
