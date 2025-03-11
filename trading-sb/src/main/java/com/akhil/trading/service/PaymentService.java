package com.akhil.trading.service;

import com.akhil.trading.domain.PaymentMethod;
import com.akhil.trading.model.PaymentOrder;
import com.akhil.trading.response.PaymentResponse;

import java.math.BigDecimal;

public interface PaymentService {
    PaymentOrder createOrder(Long userId, BigDecimal amount, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id);
    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,String paymentId);
    PaymentResponse createRazorPayPayment(Long userId, BigDecimal amount);
    PaymentResponse createStripePayment(Long userId,BigDecimal amount,Long orderId);
}
