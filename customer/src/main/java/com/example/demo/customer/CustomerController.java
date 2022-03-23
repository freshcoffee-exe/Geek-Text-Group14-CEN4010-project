package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "api/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/customers")
    @RequestMapping(path = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        return this.customerRepository.findAll();
    }

    @GetMapping("/{email}")
    public List<Customer> getCustomers(@PathVariable String email){
        return this.customerRepository.findEmail(email);
    }

//    @PutMapping
//    @RequestMapping(path = "/customers/{email}", method = RequestMethod.PUT)
//    public String updateCustomer(){
//        return this.customerRepository.updateCustomer(email);
//    }


}
