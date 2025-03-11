package com.akhil.trading.service.impl;

import com.akhil.trading.domain.PaymentMethod;
import com.akhil.trading.model.PaymentOrder;
import com.akhil.trading.repo.PaymentOrderRepo;
import com.akhil.trading.response.PaymentResponse;
import com.akhil.trading.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentOrderRepo paymentOrderRepo;
//    @Value("${stipe.api.key}")
//    private String stripeSecretKey;
//    @Value("${razorpay.api.key}")
//    private String razorPayKey;
    @Override
    public PaymentOrder createOrder(Long userId, BigDecimal amount, PaymentMethod paymentMethod) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        return null;
    }

    @Override
    public Boolean ProceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) {
        return null;
    }

    @Override
    public PaymentResponse createRazorPayPayment(Long userId, BigDecimal amount) {
        return null;
    }

    @Override
    public PaymentResponse createStripePayment(Long userId, BigDecimal amount, Long orderId) {
        return null;
    }
}
