package com.example.omway.controller.payment;

import com.example.omway.dto.payment.PaymentDto;
import com.example.omway.model.payment.Payment;
import com.example.omway.service.payment.IServicePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class ControllerPayment {

    @Autowired
    private IServicePayment servicePayment;

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Integer id) {
        return servicePayment.getPaymentById(id);
    }

    @PostMapping("/save")
    public Payment save(@RequestBody PaymentDto paymentDto){

        return servicePayment.save(paymentDto);
    }

    @PutMapping("/update")
    public Payment update(@RequestBody PaymentDto paymentDto)throws Exception{
        if(paymentDto.getPaymentId()==null){
            throw new Exception("Please, send Id value");
        }
        return servicePayment.save(paymentDto);
    }

}

