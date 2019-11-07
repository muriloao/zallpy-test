/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.controllers;

import br.com.muriloao.zallpy.dto.CustomerDTO;
import br.com.muriloao.zallpy.models.Customer;
import br.com.muriloao.zallpy.services.CustomerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author muriloao
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/cpf/{cpf}")
    @ResponseBody
    private ResponseEntity<Customer> findByCpf(@PathVariable("cpf") String cpf) {
        Optional<Customer> optCustomer = this.customerService.findByCpf(cpf);
        if (optCustomer.isPresent()) {
            return ResponseEntity.ok(optCustomer.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CustomerDTO> store(@RequestBody CustomerDTO customerDto) {
        Customer customer = this.customerService.saveOrUpdate(customerDto.toModel());
        return ResponseEntity.ok(customer.toDto());
    }
//
//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public ResponseEntity<Customer> show(@PathVariable("id") Long id) {
//        Customer customer = customerService.findById(id);
//        if (customer != null) {
//            return ResponseEntity.ok(customer);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping
//    @ResponseBody
//    public ResponseEntity index() {
//        return ResponseEntity.ok().body(this.customerService.findAll());
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @ResponseBody
//    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//        this.customerService.delete(id);
//        return ResponseEntity.ok().build();
//    }

}
