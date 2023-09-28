package com.example.omway.repository.payment;

import com.example.omway.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryPayment extends JpaRepository<Payment,Integer> {

}
