package br.com.store.service;

import br.com.store.model.Customer;
import br.com.store.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByCpf(String cpf){
        return customerRepository.findByCpf(cpf);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void deleteByCpf(String cpf){
        customerRepository.deleteByCpf(cpf);
    }
}
