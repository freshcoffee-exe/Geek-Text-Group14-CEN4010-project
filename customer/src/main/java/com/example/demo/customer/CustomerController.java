package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/newcustomer")
    public Customer createCustomer(@RequestBody Customer customer){
        return this.customerRepository.save(customer);
    }

    @GetMapping("/allcustomers")
    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }

    @GetMapping(value = "/customers/{email}")
    public List<Customer> getCustomerByEmail(@PathVariable String email){
        return customerRepository.findByEmail(email);
    }

    @PatchMapping(value = "/updatecustomer/{email}")
    public Customer updateCustomer(@PathVariable String email, @RequestBody Map<Object, Object> objectMap){
        Customer customer = customerRepository.findById(email).get();
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Customer.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, customer, value);
        });
        return this.customerRepository.save(customer);
    }
}
