package com.example.demo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "SELECT * FROM public.customers WHERE email = email", nativeQuery = true)
    List<Customer> findEmail(@Param("email") String email);

    //@Query(value = "UPDATE public.customer SET password, first_name, last_name, address WHERE email = email", nativeQuery = true)
    //List<Customer> updateCustomer(@Param("email") String password, String first_name, String last_name, String address);
}
