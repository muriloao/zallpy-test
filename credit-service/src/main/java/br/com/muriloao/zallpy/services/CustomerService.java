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

/**
 *
 * @author muriloao
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer findById(Long id) {
        return this.repository.findOne(id);
    }

    public Optional<Customer> findByCpf(String cpf) {
        return this.repository.findByCpf(cpf);
    }
}
