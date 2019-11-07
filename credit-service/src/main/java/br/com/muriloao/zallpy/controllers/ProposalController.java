/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.controllers;

import br.com.muriloao.zallpy.dto.ProposalDTO;
import br.com.muriloao.zallpy.models.Customer;
import br.com.muriloao.zallpy.models.Proposal;
import br.com.muriloao.zallpy.services.CustomerService;
import br.com.muriloao.zallpy.services.ProposalService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Murilo Oliveira
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ProposalController {

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private CustomerService customerService;

    /**
     * Get the last proposal of de customer's cpf
     *
     * @param cpf the cpf of the customer to search
     * @return the customer
     */
    @GetMapping("/cpf/{cpf}")
    @ResponseBody
    public ResponseEntity<Proposal> findByCustomerCpf(@PathVariable("cpf") String cpf) {
        Optional<Proposal> optProposal = this.proposalService.findByCustomerCpf(cpf);
        return ResponseEntity.ok(optProposal.get());
    }

    /**
     * Analyze and persist a new proposal Send the customer to Customer API to
     * register Send the proposal to Credit API to analize
     *
     * @param proposalDto a data to persist
     * @return the persist's data
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<ProposalDTO> store(@Valid @RequestBody ProposalDTO proposalDto) {
        try {
            Proposal proposal = this.proposalService.analiseAndRegisterProposal(proposalDto.toModel());
            return ResponseEntity.ok(proposal.toDto());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get the unique customer where id
     *
     * @param id the id of customer
     * @return the customer with id
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Proposal> show(@PathVariable("id") Long id) {
        Proposal creditProposal = this.proposalService.findById(id);
        if (creditProposal != null) {
            return ResponseEntity.ok(creditProposal);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get all proposals registered
     *
     * @return a list of proposals
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity index() {
        return ResponseEntity.ok().body(this.proposalService.findAll());
    }

    /**
     * Delete a proposal by id
     *
     * @param id id to delete
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        this.proposalService.delete(id);
        return ResponseEntity.ok().build();
    }

}
