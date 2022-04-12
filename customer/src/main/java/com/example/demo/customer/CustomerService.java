package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

//    public void updateCustomer(String email, Customer customer) {
//        for (int i = 0; i < getAllCustomers().size(); i++){
//            Customer c = customer.get(i)
//
//        }
//    }

//    public Customer getCustomerByEmail(String email){
//        return customerRepository.
//    }
}
