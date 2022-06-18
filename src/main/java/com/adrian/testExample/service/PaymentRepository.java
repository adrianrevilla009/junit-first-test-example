package com.adrian.testExample.service;

import com.adrian.testExample.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
