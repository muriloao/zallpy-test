/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.services;

import br.com.muriloao.zallpy.models.Customer;
import br.com.muriloao.zallpy.repositories.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author muriloao
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository orderRepository;

    public Customer save(@Validated Customer order) {
        return orderRepository.save(order);
    }

    public Customer findById(Long id) {
        return this.orderRepository.findOne(id);
    }

    public Iterable<Customer> findAll() {
        return this.orderRepository.findAll();
    }

    public void delete(Long id) {
        this.orderRepository.delete(id);
    }

    public Optional<Customer> findByCpf(String cpf) {
        return this.orderRepository.findByCpf(cpf);
    }

    public Customer saveOrUpdate(Customer customer) {
        Optional<Customer> optCustomer = this.findByCpf(customer.getCpf());
        Customer customerPersisted;
        if (optCustomer.isPresent()) {
            // update customer data
            customerPersisted = optCustomer.get();
            customerPersisted.setName(customer.getName());
            customerPersisted.setSex(customer.getSex());
        } else {
            customerPersisted = customer;
        }
        return this.save(customerPersisted);
    }
}
