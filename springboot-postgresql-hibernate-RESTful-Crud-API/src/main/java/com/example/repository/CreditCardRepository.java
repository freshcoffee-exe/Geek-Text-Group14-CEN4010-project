package com.example.repository;

import com.example.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Query(value = "SELECT * FROM public.credit_card WHERE customer_email = :customer_email", nativeQuery = true)
    List<CreditCard> findByEmail(@Param("customer_email") String customer_email);
}
