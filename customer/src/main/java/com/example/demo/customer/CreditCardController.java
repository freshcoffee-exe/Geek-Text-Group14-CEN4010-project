package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/api")
public class CreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/allcards")
    public List<CreditCard> getAllCards(){
        return this.creditCardRepository.findAll();
    }

    @PostMapping("/newcard/{customerEmail}")
    CreditCard createCard(@PathVariable String customerEmail, @RequestBody CreditCard creditCard){
        Customer customer = customerRepository.findById(customerEmail).get();
        creditCard.assignCustomer(customer);
        return creditCardRepository.save(creditCard);
    }

    @GetMapping("/{customer_email}/allCards")
    public List<CreditCard> getAllCustomersCards(@PathVariable String customer_email){
        return this.creditCardRepository.findByEmail(customer_email);
    }

}
