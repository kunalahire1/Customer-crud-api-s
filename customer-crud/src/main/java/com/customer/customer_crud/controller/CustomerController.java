package com.customer.customer_crud.controller;

import com.customer.customer_crud.customer_crud.dto.LoginRequest;
import com.customer.customer_crud.model.Customer;
import com.customer.customer_crud.repository.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping("/create")
    public String createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return "Customer added";
    }
    @PutMapping("/update/{phone}")
    public String updateCustomerByPhoneNumber(@PathVariable String phone, @RequestBody Customer customer){
        if(customerRepository.existsById(phone)){
            customer.setPhone(phone);
            customer.setPhone(phone);
            customerRepository.save(customer);
            return "Customer updated successfully";
        } else {
            return "Customer not found";
        }
    }

    @GetMapping("/get")
    public List<Customer> getAllCustomer(){
       List<Customer>lst= customerRepository.findAll();
       return  lst;
    }
    @GetMapping("/get/{phone}")
    public Customer getCustomerByPhoneNumber(@PathVariable String phone)
    {
        Customer cs=customerRepository.findById(phone).get();
        return  cs;
    }
    @DeleteMapping("/delete/{phone}")
    public String deleteCustomerByPhoneNumber(@PathVariable String phone)
    {
        customerRepository.deleteById(phone);
        return "Customer delete successfully";
    }
    @PostMapping("/login")
    public String loginCustomer(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<Customer> customer = customerRepository.findByPhoneAndEmail(loginRequest.getPhone(), loginRequest.getEmail());
        if (customer.isPresent()) {
            session.setAttribute("user", customer.get());
            return "Login successful";
        } else {
            return "Invalid phone number or email";
        }
    }
}
