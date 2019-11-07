/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.repositories;

import br.com.muriloao.zallpy.models.Proposal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author muriloao
 */
@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    @Query("SELECT p FROM Proposal p WHERE p.customer.cpf = :cpf ORDER BY id DESC")
    public List<Proposal> findByCustomerCpf(@Param("cpf") String cpf);
//    @Query("SELECT p FROM Proposal p WHERE p.customer.cpf = :cpf ORDER BY id DESC")
//    @Query(value = "SELECT * FROM proposal p WHERE p.customer.cpf = :cpf ORDER BY id DESC LIMIT 1", nativeQuery = true);
//    public List<Proposal> findByCustomerCpf(@Param("cpf") String cpf);
}
