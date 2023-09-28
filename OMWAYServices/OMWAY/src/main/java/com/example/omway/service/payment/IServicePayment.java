package com.example.omway.service.payment;

import com.example.omway.dto.payment.PaymentDto;
import com.example.omway.model.payment.Payment;
import org.springframework.stereotype.Service;

@Service
public interface IServicePayment {

    public Payment save(PaymentDto paymentDto);
    public Payment getPaymentById(Integer id);


}