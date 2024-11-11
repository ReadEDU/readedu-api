package com.team2.service;

import com.team2.dto.payments.PaymentCaptureResponse;
import com.team2.dto.payments.PaymentOrderResponse;
import jakarta.mail.MessagingException;

public interface CheckoutService {
    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl)throws MessagingException;
    PaymentCaptureResponse capturePayment(String orderId) throws MessagingException;
}
