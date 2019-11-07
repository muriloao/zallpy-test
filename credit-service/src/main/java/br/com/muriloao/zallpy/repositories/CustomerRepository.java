/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.repositories;

import br.com.muriloao.zallpy.models.Customer;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author muriloao
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public Optional<Customer> findByCpf(String cpf);

}
