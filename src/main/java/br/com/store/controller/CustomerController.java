package br.com.store.controller;

import br.com.store.model.Customer;
import br.com.store.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping(path = "/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findByCpf(@PathVariable String cpf){
        return customerService.findByCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PutMapping(path = "/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateByCpf(@RequestBody Customer customer, @PathVariable String cpf){
        customerService.findByCpf(cpf)
                .map(customerBase -> {
                    modelMapper.map(customer, customerBase);
                    customerService.save(customerBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @DeleteMapping(path = "/cpf")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByCpf(@PathVariable String cpf){
        customerService.findByCpf(cpf)
                .map(customer -> {
                    customerService.deleteByCpf(cpf);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
}
