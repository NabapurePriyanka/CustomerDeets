package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    public final CustomerRepository customerRepository;

    public  Main(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }

    public record newRequestCustomer(
            String name,
            String email,
            Integer age
    ){

    }
    @PostMapping
    public void addCustomer(@RequestBody newRequestCustomer NewRequest){
        Customer customer = new Customer();
        customer.setName(NewRequest.name);
        customer.setEmail(NewRequest.email);
        customer.setAge(NewRequest.age);
        customerRepository.save(customer);
    }

    @DeleteMapping("{customer_id}")
    public void deleteCustomer(@PathVariable("customer_id") Integer id){
        customerRepository.deleteById(id);
    }

}
