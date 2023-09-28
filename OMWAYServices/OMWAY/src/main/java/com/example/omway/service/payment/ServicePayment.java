package com.example.omway.service.payment;

import com.example.omway.dto.payment.PaymentDto;
import com.example.omway.model.payment.Payment;
import com.example.omway.model.trip.Ride;
import com.example.omway.repository.payment.IRepositoryPayment;
import com.example.omway.repository.trip.IRepositoryRide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ServicePayment implements IServicePayment {

    @Autowired
    private IRepositoryPayment repositoryPayment;


    @Autowired
    private IRepositoryRide repositoryRide;


    @Override
    public Payment getPaymentById(Integer id) {
        Optional<Payment> p1 = repositoryPayment.findById(id);
        Payment payment = new Payment();
        if (p1.isPresent()) {
            payment = p1.get();
        }

        return payment;
    }

    @Override
    public Payment save(PaymentDto paymentDto) {
        Optional<Payment> c1 = repositoryPayment.findById(paymentDto.getPaymentId());
       Payment payment = new Payment();
        if(c1.isPresent()){
            payment = c1.get();
        }
        Ride r = repositoryRide.findById(paymentDto.getRideId()).get();
        payment.setRide(r);
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setTotal(paymentDto.getTotal());
        return repositoryPayment.save(payment);

    }
}

