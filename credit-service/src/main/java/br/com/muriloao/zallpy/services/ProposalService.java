/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.services;

import br.com.muriloao.zallpy.dto.CustomerDTO;
import br.com.muriloao.zallpy.dto.ProposalDTO;
import br.com.muriloao.zallpy.enuns.Status;
import br.com.muriloao.zallpy.models.Customer;
import br.com.muriloao.zallpy.models.Proposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import br.com.muriloao.zallpy.repositories.ProposalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author muriloao
 */
@Service
public class ProposalService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProposalRepository repository;

    public Proposal save(@Validated Proposal order) {
        return this.repository.save(order);
    }

    public Proposal findById(Long id) {
        return this.repository.findOne(id);
    }

    public Iterable<Proposal> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    private ProposalDTO analyzeProposal(Proposal proposal) {
        return this.restTemplate.postForObject(System.getenv("HOST_GATEWAY") + "/credit", proposal.toDto(), ProposalDTO.class);
    }

    private CustomerDTO updateCustomer(Proposal proposal) {
        return this.restTemplate.postForObject(System.getenv("HOST_GATEWAY") + "/customer", proposal.getCustomer().toDto(), CustomerDTO.class);
    }

    public Proposal analiseAndRegisterProposal(Proposal proposal) {
        // user register
        this.updateCustomer(proposal);
        CustomerDTO responseCustomer = this.updateCustomer(proposal);

        if (responseCustomer != null) {
            try {
                // customer registeredvb
                Customer customer = this.customerService.findById(responseCustomer.getId());
                proposal.setCustomer(customer);
                proposal.setStatus(Status.PROCESSING);
                ProposalDTO analyzedProposalDto = this.analyzeProposal(proposal);
                if (Status.APPROVED.equals(analyzedProposalDto.getStatus())) {
                    // credit proposal accept
                    proposal.setStatus(Status.APPROVED);
                    // set the min and max credit value
                    proposal.setMinLimit(analyzedProposalDto.getMinLimit());
                    proposal.setMaxLimit(analyzedProposalDto.getMaxLimit());
                } else {
                    proposal.setStatus(Status.DENIED);
                    proposal.setReason(analyzedProposalDto.getReason());
                }
            } catch (Exception e) {
                e.printStackTrace();
                // set error to process after
                proposal.setStatus(Status.ERROR);
            }
            System.out.println("SAIDA " + proposal.getReason());
            return this.save(proposal);
        }

        // customer not found
        throw new RuntimeException("Customer not found");
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Optional<Proposal> findByCustomerCpf(String cpf) {
        return this.repository.findByCustomerCpf(cpf).stream().findFirst();
    }
}
